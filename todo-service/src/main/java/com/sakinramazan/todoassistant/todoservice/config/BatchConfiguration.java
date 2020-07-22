package com.sakinramazan.todoassistant.todoservice.config;

import com.sakinramazan.todoassistant.todoservice.batch.JobCompletionNotificationListener;
import com.sakinramazan.todoassistant.todoservice.batch.TodoItemProcessor;
import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    private final DataSource dataSource;

    @Bean
    public FlatFileItemReader<Todo> reader() {
        FlatFileItemReader<Todo> reader = new FlatFileItemReader<>();
        // You can specify the file and format here
        reader.setResource(new ClassPathResource("file.csv"));
        // Tokenize the users and import them as entity to database
        // via headline and details , you can differentiate that
        reader.setLineMapper(new DefaultLineMapper<Todo>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("headline", "details");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Todo>() {
                    {
                        setTargetType(Todo.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public TodoItemProcessor processor() {
        return new TodoItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Todo> writer() {
        JdbcBatchItemWriter<Todo> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO todo (headline, details) VALUES (:headline, :details)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importTodoJob").incrementer(
                new RunIdIncrementer()).listener(listener).flow(step1()).end().build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Todo, Todo>chunk(10).reader(reader()).processor(processor()).writer(writer()).build();
    }
}

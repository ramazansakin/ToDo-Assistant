//package com.sakinramazan.userservice.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableCaching
//@Slf4j
//public class RedisConfig {
//
//    @Bean
//    RedisConnectionFactory jedisConnectionFactory() throws Exception {
//        URI redisUri = new URI("localhost:6379");
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//        redisConnectionFactory.setHostName(redisUri.getHost());
//        redisConnectionFactory.setPort(redisUri.getPort());
//        redisConnectionFactory.setPassword(redisUri.getUserInfo().split(":", 2)[1]);
//        redisConnectionFactory.setUsePool(true);
//        return redisConnectionFactory;
//    }
//
//    @Bean
//    RedisTemplate<Object, Object> redisTemplate() {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
//        try {
//            redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        // configure and return an implementation of Spring's CacheManager SPI
//        RedisCacheManager manager = new RedisCacheManager(redisTemplate());
//        List<String> caches = new ArrayList<String>();
//        caches.add("users");
//        caches.add("addresses");
//        manager.setCacheNames(caches);
//        return manager;
//    }
//}

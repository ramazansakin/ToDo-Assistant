package com.sakinramazan.todoassistant.todoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @NotNull(message = "{validation.messages.airport.name}")
    private String headline;

    private String details;

    // Transient tells "do not persist this field"
//    @Transient
//    private List<Address> addresses;
}

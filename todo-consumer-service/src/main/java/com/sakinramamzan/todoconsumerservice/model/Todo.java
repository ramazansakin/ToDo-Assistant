package com.sakinramamzan.todoconsumerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo implements Serializable {

    private Integer id;
    private String headline;
    private String details;
    private Integer userId;

}

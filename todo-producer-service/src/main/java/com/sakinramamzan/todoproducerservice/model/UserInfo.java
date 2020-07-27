package com.sakinramamzan.todoproducerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;
    private String lastname;
    private String email;
    private String addressInfo;
}

package com.sakinramazan.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String lastname;
    private String username;
    private String email;

//    I want to hide user password and address
//    and just we need to do that is defining DTO
//    private String password;
//    private Address address;

}

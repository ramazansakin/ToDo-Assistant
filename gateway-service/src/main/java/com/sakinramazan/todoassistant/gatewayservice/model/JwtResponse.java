package com.sakinramazan.todoassistant.gatewayservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private final String jwttoken;
}

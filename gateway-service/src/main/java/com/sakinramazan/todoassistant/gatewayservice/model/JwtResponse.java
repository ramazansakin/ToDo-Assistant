package com.sakinramazan.todoassistant.gatewayservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class JwtResponse implements Serializable {

    private final String jwttoken;
}

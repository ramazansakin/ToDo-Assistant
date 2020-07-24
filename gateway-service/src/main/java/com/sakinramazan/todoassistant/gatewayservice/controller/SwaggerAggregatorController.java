package com.sakinramazan.todoassistant.gatewayservice.controller;

//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Primary
//@EnableAutoConfiguration
//public class SwaggerAggregatorController implements SwaggerResourcesProvider {
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//
//        SwaggerResource userServiceResource = new SwaggerResource();
//        userServiceResource.setName("user-service");
//        userServiceResource.setLocation("/user-swagger/v2/api-docs");
//        userServiceResource.setSwaggerVersion("2.0");
//        resources.add(userServiceResource);
//
//        SwaggerResource todoServiceResource = new SwaggerResource();
//        todoServiceResource.setName("todo-service");
//        todoServiceResource.setLocation("/todo-swagger/v2/api-docs");
//        todoServiceResource.setSwaggerVersion("2.0");
//
//        resources.add(todoServiceResource);
//        return resources;
//    }
//}

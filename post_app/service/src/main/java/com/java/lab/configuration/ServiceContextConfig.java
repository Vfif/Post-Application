package com.java.lab.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.java.lab")
public class ServiceContextConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

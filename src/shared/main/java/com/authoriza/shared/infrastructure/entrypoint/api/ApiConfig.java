package com.authoriza.shared.infrastructure.entrypoint.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebMvc
public class ApiConfig {
    @Bean
    public RequestMappingHandlerMapping requestMappingHandler() {
        return new RequestMappingHandlerMapping();
    }
}

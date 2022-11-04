package com.authoriza.identityaccess;

import com.authoriza.shared.domain.service.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"com.authoriza.shared", "com.authoriza.identityaccess"}
)
public class AuthorizaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizaApplication.class, args);
    }
}

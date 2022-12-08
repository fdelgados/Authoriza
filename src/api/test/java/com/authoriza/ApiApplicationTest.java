package com.authoriza;

import com.authoriza.api.ApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ApiApplication.class)
@SpringBootTest
public class ApiApplicationTest {
    @Test
    public void contextLoads() throws Exception {
    }
}

package com.authoriza.api.account.infrastructure.entrypoint.api.spring;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping("/api/v0/accounts")
public @interface AccountsApiPrefix {
    @AliasFor(annotation = Component.class)
    String value() default "";

    String prefix() default "accounts";
}

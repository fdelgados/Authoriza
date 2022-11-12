package com.authoriza.shared.infrastructure.spring.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredValidator.class)
@Documented
public @interface Required {
    String message() default "Field required";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

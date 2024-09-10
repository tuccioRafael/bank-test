package com.bank.test.constraints;

import com.bank.test.validation.FutureOrToday;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FutureOrToday.class)
@Target( {ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureOrTodayValidate {

    String message() default "A data de transferência precisa ser hoje ou uma data futura";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

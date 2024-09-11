package com.bank.test.constraints;
import com.bank.test.validation.TransferDate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TransferDate.class)
@Target( {ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface TransferDateValidate {
    String message() default "A data de transferencia precisa ser de até 50 dias";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

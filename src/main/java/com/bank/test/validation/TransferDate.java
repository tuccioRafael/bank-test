package com.bank.test.validation;

import com.bank.test.constraints.TransferDateValidate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TransferDate implements ConstraintValidator<TransferDateValidate, LocalDate> {
    @Override
    public void initialize(TransferDateValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if(date == null) return true;
        return ChronoUnit.DAYS.between(LocalDate.now(), date) <= 50;
    }
}

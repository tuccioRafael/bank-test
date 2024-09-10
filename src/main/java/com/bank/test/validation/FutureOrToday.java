package com.bank.test.validation;

import com.bank.test.constraints.FutureOrTodayValidate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureOrToday implements ConstraintValidator<FutureOrTodayValidate, LocalDate> {

    @Override
    public void initialize(FutureOrTodayValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if(date == null) {
            return true;
        }
        return !date.isBefore(LocalDate.now());
    }
}

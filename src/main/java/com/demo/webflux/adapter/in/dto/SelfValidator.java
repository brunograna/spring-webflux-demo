package com.demo.webflux.adapter.in.dto;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public abstract class SelfValidator<T> {
    
    private final Validator validator;
    
    protected SelfValidator() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    public void confirmIsValid() throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

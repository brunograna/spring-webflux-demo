package com.demo.webflux.commons;

import com.demo.webflux.adapter.in.exception.TreatableConstraintViolationException;

import javax.validation.*;
import java.util.Set;

public abstract class SelfValidator<T> {
    
    private final Validator validator;
    
    public SelfValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    public void confirmIsValid() throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new TreatableConstraintViolationException(violations);
        }
    }
}

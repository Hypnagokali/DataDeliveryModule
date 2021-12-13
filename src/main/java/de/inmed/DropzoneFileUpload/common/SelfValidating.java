package de.inmed.DropzoneFileUpload.common;

import javax.validation.*;
import java.util.Set;

public class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

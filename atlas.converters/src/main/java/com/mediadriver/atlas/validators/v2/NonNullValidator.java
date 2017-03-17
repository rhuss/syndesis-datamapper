package com.mediadriver.atlas.validators.v2;

/**
 * Created by on 3/12/17.
 */
public class NonNullValidator implements Validator {

    private String violationMessage;

    private String field;

    public NonNullValidator(String field, String violationMessage) {
        this.field = field;
        this.violationMessage = violationMessage;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, AtlasMappingError.Level.ERROR);
    }

    @Override
    public void validate(Object target, Errors errors, AtlasMappingError.Level level) {

        if (target == null) {
            errors.addError(new AtlasMappingError(field, target, this.violationMessage, level));
        } else if (target.getClass().isAssignableFrom(String.class)) {
            String value = (String) target;
            if (value.trim().isEmpty()) {
                errors.addError(new AtlasMappingError(field, target, this.violationMessage, level));
            }
        }

    }
}

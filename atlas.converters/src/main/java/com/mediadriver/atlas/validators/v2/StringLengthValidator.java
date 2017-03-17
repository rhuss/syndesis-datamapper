package com.mediadriver.atlas.validators.v2;

/**
 * Created by slepage on 3/12/17.
 */
public class StringLengthValidator implements Validator {


    private String violationMessage;

    private int minLength = 1;

    private int maxLength = Integer.MAX_VALUE;

    private String field;


    public StringLengthValidator(String field, String violationMessage, int minLength, int maxLength) {
        this.field = field;
        this.violationMessage = violationMessage;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean supports(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, AtlasMappingError.Level.ERROR);
    }

    @Override
    public void validate(Object target, Errors errors, AtlasMappingError.Level level) {
        String value = (String) target;
        if (value.isEmpty() || value.length() > maxLength || value.length() < minLength) {
            errors.addError(new AtlasMappingError(field, target, this.violationMessage, level));
        }

    }
}

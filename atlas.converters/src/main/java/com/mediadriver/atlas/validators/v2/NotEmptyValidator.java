package com.mediadriver.atlas.validators.v2;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by slepage on 3/14/17.
 */
public class NotEmptyValidator implements Validator {

    private String violationMessage;

    private String field;

    public NotEmptyValidator(String field, String violationMessage) {
        this.violationMessage = violationMessage;
        this.field = field;
    }

    @Override
    public boolean supports(Class clazz) {

        if (clazz.isAssignableFrom(List.class) || clazz.isAssignableFrom(Map.class)
                || clazz.isAssignableFrom(Set.class) || clazz.isAssignableFrom(Collection.class)) {
            return true;
        }

        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, AtlasMappingError.Level.ERROR);
    }

    @Override
    public void validate(Object target, Errors errors, AtlasMappingError.Level level) {

        if (((Collection) target).isEmpty()) {
            errors.addError(new AtlasMappingError(field, target, this.violationMessage, level));
        }
    }
}

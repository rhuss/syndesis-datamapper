package com.mediadriver.atlas.validators.v2;

/**
 * Created by slepage on 3/12/17.
 */
public interface Validator {


    boolean supports(Class clazz);

    void validate(Object target, Errors errors);

    void validate(Object target, Errors errors, AtlasMappingError.Level level);
}

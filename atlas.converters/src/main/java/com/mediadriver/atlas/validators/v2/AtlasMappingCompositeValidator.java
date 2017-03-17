package com.mediadriver.atlas.validators.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slepage on 3/12/17.
 */
public class AtlasMappingCompositeValidator implements Validator {


    private List<Validator> validators;


    public AtlasMappingCompositeValidator(List<Validator> validators) {
        this.validators = new ArrayList<>(validators);
    }


    public AtlasMappingCompositeValidator(Validator... validators) {
        this.validators = new ArrayList<>();
        for (Validator validator : validators) {
            this.validators.add(validator);
        }
    }

    @Override
    public boolean supports(Class clazz) {
        for (Validator validator : validators) {
            if (validator.supports(clazz)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, AtlasMappingError.Level.ERROR);

    }

    @Override
    public void validate(Object target, Errors errors, AtlasMappingError.Level level) {
        for (Validator validator : validators) {
            validator.validate(target, errors, level);
        }
    }
}

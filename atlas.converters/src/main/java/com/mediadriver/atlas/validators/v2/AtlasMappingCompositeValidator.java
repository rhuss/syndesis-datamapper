/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

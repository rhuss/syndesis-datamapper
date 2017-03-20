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

/**
 * Created by slepage on 3/15/17.
 */
public class PositiveIntegerValidator implements Validator {


    private String violationMessage;

    private String field;

    public PositiveIntegerValidator(String field, String violationMessage) {
        this.violationMessage = violationMessage;
        this.field = field;
    }

    @Override
    public boolean supports(Class clazz) {

        if (Integer.class.isAssignableFrom(clazz) || String.class.isAssignableFrom(clazz)) {
            return true;
        }

        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        this.validate(target, errors, AtlasMappingError.Level.ERROR);
    }

    @Override
    public void validate(Object target, Errors errors, AtlasMappingError.Level level) {
        Integer value = (Integer) target;
        if (value == null || value < 0) {
            errors.addError(new AtlasMappingError(field, target, violationMessage, level));
        }
    }
}

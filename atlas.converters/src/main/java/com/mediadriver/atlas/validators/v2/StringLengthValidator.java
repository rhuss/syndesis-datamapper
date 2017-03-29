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

import com.mediadriver.atlas.validators.v2.core.AtlasMappingError;

/**
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

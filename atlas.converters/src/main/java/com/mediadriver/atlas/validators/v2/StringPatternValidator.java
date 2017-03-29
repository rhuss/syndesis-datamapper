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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class StringPatternValidator implements Validator {


    private String violationMessage;
    private String pattern;
    private String field;
    private boolean useMatch;


    public StringPatternValidator(String field, String violationMessage, String pattern) {
        this(field, violationMessage, pattern, false);
    }

    public StringPatternValidator(String field, String violationMessage, String pattern, boolean useMatch) {
        this.violationMessage = violationMessage;
        this.pattern = pattern;
        this.field = field;
        this.useMatch = useMatch;
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
        //assume a RegEx pattern
        Pattern regEx = Pattern.compile(pattern);


        if (target != null && supports(target.getClass())) {
            String value = (String) target;
            Matcher m = regEx.matcher(value);
            if (useMatch) {
                if (!m.matches()) {
                    errors.addError(new AtlasMappingError(field, target, this.violationMessage, level));
                }
            } else {
                if (m.find()) {
                    errors.addError(new AtlasMappingError(field, target, this.violationMessage, level));
                }
            }
        }
    }
}

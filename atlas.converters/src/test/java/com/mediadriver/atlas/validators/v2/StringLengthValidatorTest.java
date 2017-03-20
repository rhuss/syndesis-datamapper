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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by slepage on 3/14/17.
 */
public class StringLengthValidatorTest {

    private StringLengthValidator validator;

    private Errors errors;

    @Test
    public void supports() throws Exception {
        validator = new StringLengthValidator("qwerty", "Must be of this length", 1, 10);
        assertTrue(validator.supports(String.class));

    }

    @Test
    public void doesnt_supports() throws Exception {
        validator = new StringLengthValidator("qwerty", "Must be of this length", 1, 10);
        assertFalse(validator.supports(Integer.class));
    }

    @Test
    public void validate() throws Exception {
        validator = new StringLengthValidator("qwerty", "Must be of this length", 1, 10);
        errors = new AtlasMappingErrors();
        String pass = "1112332";
        validator.validate(pass, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validate_invalid() throws Exception {
        validator = new StringLengthValidator("qwerty", "Must be of this length", 1, 10);
        errors = new AtlasMappingErrors();
        String pass = "";
        validator.validate(pass, errors);
        assertTrue(errors.hasErrors());
        assertThat(errors.getAllErrors().size(), is(1));

        AtlasMappingError error = errors.getAllErrors().get(0);
        assertNotNull(error);

        assertTrue(error.getRejectedValue().equals(""));
        assertTrue(error.getDefaultMessage().equals("Must be of this length"));
        assertTrue(error.getField().equals("qwerty"));
    }

}
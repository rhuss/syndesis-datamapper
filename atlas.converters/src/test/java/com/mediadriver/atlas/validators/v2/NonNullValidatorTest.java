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
import com.mediadriver.atlas.validators.v2.core.AtlasMappingErrors;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 */
public class NonNullValidatorTest {

    private NonNullValidator validator;

    private Errors errors;

    @Test
    public void supports() throws Exception {
        validator = new NonNullValidator("qwerty", "Cannot be null");
        assertTrue(validator.supports(String.class));
        assertTrue(validator.supports(Integer.class));
        assertTrue(validator.supports(Double.class));

    }

    @Test
    public void validate() throws Exception {
        validator = new NonNullValidator("qwerty", "Cannot be null");
        String notNull = "notNull";
        errors = new AtlasMappingErrors();
        validator.validate(notNull, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validate_invalid() throws Exception {
        validator = new NonNullValidator("qwerty", "Cannot be null");
        errors = new AtlasMappingErrors();
        validator.validate(null, errors);
        assertTrue(errors.hasErrors());
        assertThat(errors.getAllErrors().size(), is(1));

        AtlasMappingError error = errors.getAllErrors().get(0);
        assertNotNull(error);

        assertNull(error.getRejectedValue());
        assertTrue(error.getDefaultMessage().equals("Cannot be null"));
        assertTrue(error.getField().equals("qwerty"));

        String empty = "";
        errors.getAllErrors().clear();

        validator.validate(empty, errors);

        assertTrue(errors.hasErrors());
        assertThat(errors.getAllErrors().size(), is(1));
    }

}
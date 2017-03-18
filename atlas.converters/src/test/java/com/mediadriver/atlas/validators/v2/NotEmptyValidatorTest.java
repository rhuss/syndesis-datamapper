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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by slepage on 3/15/17.
 */
public class NotEmptyValidatorTest {

    private NotEmptyValidator validator;
    private Errors errors;

    @Test
    public void supports() throws Exception {
        validator = new NotEmptyValidator("test.field", "Collection should not be empty");
        assertTrue(validator.supports(Map.class));
        assertTrue(validator.supports(List.class));
        assertTrue(validator.supports(Set.class));
        assertTrue(validator.supports(Collection.class));
    }

    @Test
    public void not_supports() throws Exception {
        validator = new NotEmptyValidator("test.field", "Collection should not be empty");
        assertFalse(validator.supports(HashMap.class));
    }

    @Test
    public void validate() throws Exception {
        List<String> stuff = new ArrayList<>();
        stuff.add("one");
        stuff.add("two");

        errors = new AtlasMappingErrors();
        validator = new NotEmptyValidator("test.field", "Collection should not be empty");
        validator.validate(stuff, errors);
        assertFalse(errors.hasErrors());

        validator.validate(stuff, errors, AtlasMappingError.Level.WARN);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void invalid_validate() throws Exception {
        List<String> stuff = new ArrayList<>();
        errors = new AtlasMappingErrors();
        validator = new NotEmptyValidator("test.field", "Collection should not be empty");
        validator.validate(stuff, errors);
        assertTrue(errors.hasErrors());
        assertThat(errors.getErrorCount(), is(1));
        assertFalse(errors.hasWarnings());
        assertFalse(errors.hasInfos());
    }

}
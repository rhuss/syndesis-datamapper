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
package com.mediadriver.atlas.validators.v2.core;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 */
public class AtlasMappingErrorTest {
    private AtlasMappingError error = new AtlasMappingError("test.field", null, "Error message", AtlasMappingError.Level.ERROR);
    private AtlasMappingError warning = new AtlasMappingError("test.field.one", "", "Warning message", AtlasMappingError.Level.WARN);
    private AtlasMappingError info = new AtlasMappingError("test.field.two", "qwerty", "Information message", AtlasMappingError.Level.INFO);

    @Test
    public void testGetField() throws Exception {
        assertTrue("test.field".equals(error.getField()));
        assertTrue("test.field.one".equals(warning.getField()));
        assertTrue("test.field.two".equals(info.getField()));
    }

    @Test
    public void testGetRejectedValue() throws Exception {
        assertNull(error.getRejectedValue());
        assertTrue(((String) warning.getRejectedValue()).isEmpty());
        assertTrue(info.getRejectedValue().equals("qwerty"));
    }

    @Test
    public void testGetDefaultMessage() throws Exception {
        assertTrue(error.getDefaultMessage().equals("Error message"));
        assertTrue(warning.getDefaultMessage().equals("Warning message"));
        assertTrue(info.getDefaultMessage().equals("Information message"));
    }

    @Test
    public void testGetLevel() throws Exception {
        assertTrue(error.getLevel().compareTo(AtlasMappingError.Level.ERROR) == 0);
        assertTrue(warning.getLevel().compareTo(AtlasMappingError.Level.WARN) == 0);
        assertTrue(info.getLevel().compareTo(AtlasMappingError.Level.INFO) == 0);
    }

    @Test
    public void testToString() throws Exception {
        assertThat(error.getField(), is("test.field"));
        assertThat(error.getRejectedValue(), nullValue());
        assertThat(error.getDefaultMessage(), is("Error message"));
        assertThat(error.getLevel(), is(AtlasMappingError.Level.ERROR));

        assertThat(warning.getField(), is("test.field.one"));
        assertThat(warning.getRejectedValue(), CoreMatchers.is(""));
        assertThat(warning.getDefaultMessage(), is("Warning message"));
        assertThat(warning.getLevel(), is(AtlasMappingError.Level.WARN));

        assertThat(info.getField(), is("test.field.two"));
        assertThat(info.getRejectedValue(), CoreMatchers.is("qwerty"));
        assertThat(info.getDefaultMessage(), is("Information message"));
        assertThat(info.getLevel(), is(AtlasMappingError.Level.INFO));
    }

    @Test
    public void testEquals() throws Exception {
        assertFalse(error.equals(info));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(error.hashCode(), -914327732);
        assertEquals(warning.hashCode(), -187767976);
        assertEquals(info.hashCode(), -1746235594);
    }

}
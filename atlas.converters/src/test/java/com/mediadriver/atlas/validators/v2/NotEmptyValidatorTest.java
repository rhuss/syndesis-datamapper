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
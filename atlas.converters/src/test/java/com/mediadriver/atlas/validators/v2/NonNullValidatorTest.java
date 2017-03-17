package com.mediadriver.atlas.validators.v2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by slepage on 3/14/17.
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
        String pass = null;
        validator.validate(pass, errors);
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
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
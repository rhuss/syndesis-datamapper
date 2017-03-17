package com.mediadriver.atlas.validators.v2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by slepage on 3/12/17.
 */
public class StringPatternValidatorTest {

    private StringPatternValidator validator;


    @Test
    public void supports() throws Exception {
        validator = new StringPatternValidator("qwerty", "Must match .*", ".*");
        assertTrue(validator.supports(String.class));
    }

    @Test
    public void not_Supports() throws Exception {
        validator = new StringPatternValidator("qwerty", "Must match [0-9_.]", "[0-9_.]");
        assertFalse(validator.supports(Double.class));
    }

    @Test
    public void validate() throws Exception {
        Errors errors = new AtlasMappingErrors();
        validator = new StringPatternValidator("qwerty", "Must match [^A-Za-z0-9_.]", "[^A-Za-z0-9_.]");
        validator.validate("This. &* should result in an error", errors);
        assertTrue(errors.hasErrors());
        errors.getAllErrors().clear();
        assertFalse(errors.hasErrors());
        validator.validate("This_isafineexample.whatever1223", errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validate_UsingMatch() throws Exception {
        Errors errors = new AtlasMappingErrors();
        validator = new StringPatternValidator("qwerty", "Must match [0-9]+", "[0-9]+", true);
        validator.validate("0333", errors);
        assertFalse(errors.hasErrors());

        validator = new StringPatternValidator("qwerty", "Must match [0-9]", "[0-9]", true);
        validator.validate("This_isafineexample.whatever", errors);
        assertTrue(errors.hasErrors());
    }

}
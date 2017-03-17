package com.mediadriver.atlas.validators.v2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slepage on 3/12/17.
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

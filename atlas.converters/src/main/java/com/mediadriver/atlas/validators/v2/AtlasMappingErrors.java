package com.mediadriver.atlas.validators.v2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by slepage on 3/12/17.
 */
public class AtlasMappingErrors implements Errors {

    private final List<AtlasMappingError> errors = new LinkedList<>();

    @Override
    public void addError(AtlasMappingError error) {
        this.errors.add(error);

    }

    @Override
    public List<AtlasMappingError> getAllErrors() {
        return errors;
    }

    @Override
    public boolean hasErrors() {
        if (!errors.isEmpty()) {
            for (AtlasMappingError error : errors) {
                if (AtlasMappingError.Level.ERROR.compareTo(error.getLevel()) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasWarnings() {

        if (!errors.isEmpty()) {
            for (AtlasMappingError error : errors) {
                if (AtlasMappingError.Level.WARN.compareTo(error.getLevel()) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasInfos() {
        if (!errors.isEmpty()) {
            for (AtlasMappingError error : errors) {
                if (AtlasMappingError.Level.INFO.compareTo(error.getLevel()) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int getErrorCount() {
        if (!errors.isEmpty()) {
            return errors.size();
        }
        return 0;
    }
}

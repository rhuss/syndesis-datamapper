package com.mediadriver.atlas.validators.v2;

import java.util.List;

/**
 * Created by slepage on 3/12/17.
 */
public interface Errors {

//    void addAllErrors(Errors errors);

    void addError(AtlasMappingError error);

    List<AtlasMappingError> getAllErrors();

    boolean hasErrors();

    boolean hasWarnings();

    boolean hasInfos();

    int getErrorCount();

}

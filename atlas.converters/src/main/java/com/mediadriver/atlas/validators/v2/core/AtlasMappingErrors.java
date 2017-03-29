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

import com.mediadriver.atlas.validators.v2.Errors;

import java.util.LinkedList;
import java.util.List;

/**
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

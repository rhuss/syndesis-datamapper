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

import com.mediadriver.atlas.v2.AtlasMapping;

import java.util.Objects;

/**
 * Created by slepage on 3/12/17.
 */
public class AtlasMappingError {

    AtlasMapping mapping = new AtlasMapping();
    private final String field;

    private final Object rejectedValue;

    private final String defaultMessage;

    private Level level;

    public enum Level {
        WARN, INFO, ERROR
    }

    public AtlasMappingError(String field, Object rejectedValue, String defaultMessage, Level level) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.defaultMessage = defaultMessage;
        this.level = level;
    }

    public AtlasMappingError(String field, Object rejectedValue, String defaultMessage) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.defaultMessage = defaultMessage;
        this.level = Level.INFO;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "AtlasMappingError{" +
                "mapping=" + mapping +
                ", field='" + field + '\'' +
                ", rejectedValue=" + rejectedValue +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtlasMappingError)) return false;
        AtlasMappingError that = (AtlasMappingError) o;
        return Objects.equals(field, that.field) &&
                Objects.equals(rejectedValue, that.rejectedValue) &&
                Objects.equals(defaultMessage, that.defaultMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, rejectedValue, defaultMessage);
    }
}

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

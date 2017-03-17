package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

/**
 * Convert a Boolean to the targeted primitive type.
 */
public class BooleanConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // we want a new object
        return new Boolean(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Byte convertToByte(Boolean value) throws AtlasConversionException, AtlasUnsupportedException {
        if (value == null) {
            return null;
        }
        throw new AtlasUnsupportedException();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Character convertToCharacter(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return (char) (value ? 1 : 0);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Double convertToDouble(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value ? 1.0d : 0.0d;
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Float convertToFloat(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        return value ? 1.0f : 0.0f;
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Integer convertToInteger(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value ? 1 : 0;
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Long convertToLong(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value ? 1l : 0l;
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Short convertToShort(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return (short) (value ? 1 : 0);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public String convertToString(Boolean value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf((value ? "true" : "false"));
    }
}

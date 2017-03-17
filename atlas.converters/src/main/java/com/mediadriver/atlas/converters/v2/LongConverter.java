package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

/**
 *
 */
public class LongConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value == 0L || value == 1L) {
            if (value == 1L) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        throw new AtlasConversionException();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     * @throws AtlasUnsupportedException
     */
    public Byte convertToByte(Long value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        if (value < Character.MIN_VALUE || value > Character.MAX_VALUE) {
            throw new AtlasConversionException();
        }

        return (char) value.intValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Double convertToDouble(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.doubleValue();
    }

    public Float convertToFloat(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value.floatValue() == 0.0f || value.floatValue() == -0.0f) {
            return value.floatValue();
        }
        return value.floatValue();
    }

    public Integer convertToInteger(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new AtlasConversionException();
        }
        return value.intValue();
    }

    public Long convertToLong(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //we want a copy of value
        return new Long(value);
    }

    public Short convertToShort(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Short.MAX_VALUE || value < Short.MIN_VALUE) {
            throw new AtlasConversionException();
        }
        return value.shortValue();
    }

    public String convertToString(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}

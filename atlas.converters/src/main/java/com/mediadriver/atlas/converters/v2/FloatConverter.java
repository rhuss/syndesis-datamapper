package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

/**
 *
 */
public class FloatConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value == 0.0 || value == 1.0) {
            if (value == 1) {
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
     */
    public Byte convertToByte(Float value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(Float value) throws AtlasConversionException {
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
    public Double convertToDouble(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        if (value == 0.0f) {
            return value.doubleValue();
        }

        return value.doubleValue();
    }

    public Float convertToFloat(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //we want a copy of value
        return value.floatValue();
    }

    public Integer convertToInteger(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
            throw new AtlasConversionException();
        }
        return value.intValue();
    }

    public Long convertToLong(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Long.MAX_VALUE || value < Long.MIN_VALUE) {
            throw new AtlasConversionException();
        }
        return value.longValue();
    }

    public Short convertToShort(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Short.MAX_VALUE || value < Short.MIN_VALUE) {
            throw new AtlasConversionException();
        }
        return value.shortValue();
    }

    public String convertToString(Float value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}

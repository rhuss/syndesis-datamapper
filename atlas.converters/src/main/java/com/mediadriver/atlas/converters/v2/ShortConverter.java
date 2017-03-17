package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

/**
 *
 */
public class ShortConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value == 0 || value == 1) {
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
     * @throws AtlasUnsupportedException
     */
    public Byte convertToByte(Short value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        if (value < Character.MIN_VALUE) {
            throw new AtlasConversionException();
        }

        return (char) value.intValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Double convertToDouble(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.doubleValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Float convertToFloat(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.floatValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Integer convertToInteger(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.intValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Long convertToLong(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.longValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Short convertToShort(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //we want a copy of the value
        return new Short(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public String convertToString(Short value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}

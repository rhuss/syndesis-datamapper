package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

/**
 *
 */
public class CharacterConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // check for 1|0 or T|F|t|f (ASCII Integer value)
        if ((value == 49 || value == 48) || (value == 84 || value == 70) || (value == 116 || value == 102)) {
            if (value == 49 || value == 116 || value == 84) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
        // assuming anything not able to convert to a Boolean should be an exception condition
        throw new AtlasConversionException();

    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Byte convertToByte(Character value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // we want new Character from the value
        return new Character(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Double convertToDouble(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return Double.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Float convertToFloat(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return Float.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Integer convertToInteger(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Long convertToLong(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return Long.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Short convertToShort(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // only care if the char is larger than the short MAX
        if (value > Short.MAX_VALUE) {
            throw new AtlasConversionException();
        }
        return (short) value.charValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public String convertToString(Character value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}

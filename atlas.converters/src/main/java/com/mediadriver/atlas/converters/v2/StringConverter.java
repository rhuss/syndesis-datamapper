package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

import java.math.BigDecimal;

/**
 *
 */
public class StringConverter {


    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value.equals("0") || value.equalsIgnoreCase("f") || value.equals("false")) {
            return Boolean.FALSE;
        } else if (value.equals("1") || value.equalsIgnoreCase("t") || value.equals("true")) {
            return Boolean.TRUE;
        }
        throw new AtlasConversionException();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     * @throws AtlasUnsupportedException
     */
    public Byte convertToByte(String value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // empty or greater than 1 char String throws Exception
        if (value.isEmpty() || value.length() > 1) {
            throw new AtlasConversionException();
        } else if (value.charAt(0) < Character.MIN_VALUE || value.charAt(0) > Character.MAX_VALUE) {
            throw new AtlasConversionException();
        }
        return value.charAt(0);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Double convertToDouble(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        try {
            Double d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            throw new AtlasConversionException(nfe);
        }

        if (Double.valueOf(value) == 0.0d || Double.valueOf(value) == -0.0d) {
            return Double.valueOf(value);
        }
        if (Double.valueOf(value) < Double.MIN_VALUE || Double.valueOf(value) > Double.MAX_VALUE) {
            throw new AtlasConversionException();
        }

        return Double.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Float convertToFloat(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //check we can make a float of the String
        try {
            Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            throw new AtlasConversionException(nfe);
        }

        BigDecimal bd = new BigDecimal(value);

        //handle 0.0f && -0.0  (floats suck)
        if (bd.floatValue() == 0.0f || bd.floatValue() == -0.0) {
            return Float.valueOf(value);
        }

        if (bd.floatValue() < Float.MIN_VALUE || bd.floatValue() > Float.MAX_VALUE) {
            throw new AtlasConversionException();
        }

        return Float.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Integer convertToInteger(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //check we can make a int of the String
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new AtlasConversionException(nfe);
        }
        if (Integer.valueOf(value) < Integer.MIN_VALUE || Integer.valueOf(value) > Integer.MAX_VALUE) {
            throw new AtlasConversionException();
        }
        return Integer.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Long convertToLong(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //check we can make a long of the String
        try {
            Long l = Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            throw new AtlasConversionException(nfe);
        }
        return Long.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Short convertToShort(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //check we can make a short of the String
        try {
            Short.parseShort(value);
        } catch (NumberFormatException nfe) {
            throw new AtlasConversionException(nfe);
        }
        return Short.valueOf(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public String convertToString(String value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //we want a copy of value
        return new String(value);
    }
}

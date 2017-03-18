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
package com.mediadriver.atlas.converters.v2;


import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;

/**
 *
 */
public class DoubleConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value == 0.0 || value == 1.0) {
            if (value == 1.0) {
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
    public Byte convertToByte(Double value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        if (value < Character.MIN_VALUE || value > Character.MAX_VALUE) {
            throw new AtlasConversionException();
        }

        return (char) value.doubleValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Double convertToDouble(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //we want a copy of the value.
        return new Double(value);
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Float convertToFloat(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Float.MAX_VALUE || (value < Float.MIN_VALUE && value != 0)) {
            throw new AtlasConversionException();
        }
        return value.floatValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Integer convertToInteger(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Integer.MAX_VALUE) {
            throw new AtlasConversionException();
        }
        return value.intValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Long convertToLong(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Long.MAX_VALUE) {
            throw new AtlasConversionException();
        }
        return value.longValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Short convertToShort(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Short.MAX_VALUE || value < Short.MIN_VALUE) {
            throw new AtlasConversionException();
        }
        return value.shortValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public String convertToString(Double value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}

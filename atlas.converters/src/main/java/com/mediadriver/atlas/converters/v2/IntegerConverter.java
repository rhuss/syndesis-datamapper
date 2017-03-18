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
public class IntegerConverter {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Boolean convertToBoolean(Integer value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // 1 == True, 0 == False
        if (value == 0 || value == 1) {
            return value == 1;
        } else {
            // any other value
            throw new AtlasConversionException();
        }
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Byte convertToByte(Integer value) throws AtlasConversionException, AtlasUnsupportedException {
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
    public Character convertToCharacter(Integer value) throws AtlasConversionException {
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
    public Double convertToDouble(Integer value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value.doubleValue() == 0.0d || value.doubleValue() == -0.0d) {
            return value.doubleValue();
        }
        return value.doubleValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Float convertToFloat(Integer value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        if ((value.floatValue() == 0.0f) || (value.floatValue() == -0.0f)) {
            return value.floatValue();
        }
        return value.floatValue();
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    public Integer convertToInteger(Integer value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        //we want a copy of value
        return new Integer(value);
    }

    public Long convertToLong(Integer value) throws AtlasConversionException {
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
    public Short convertToShort(Integer value) throws AtlasConversionException {
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
    public String convertToString(Integer value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}

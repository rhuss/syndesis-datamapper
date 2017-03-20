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

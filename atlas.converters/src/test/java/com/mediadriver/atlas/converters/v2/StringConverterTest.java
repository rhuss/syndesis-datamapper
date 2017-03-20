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
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 3/7/17.
 */
public class StringConverterTest {
    private StringConverter converter = new StringConverter();

    @Test
    public void convertToBoolean() throws Exception {
        String f = "0";
        String t = "1";
        String Ttrue = "T";
        String Ftrue = "F";
        String tTrue = "t";
        String fFalse = "f";

        String aTrue = "true";
        String aFalse = "false";

        Boolean b = converter.convertToBoolean(t);
        assertNotNull(b);
        assertTrue(b);

        b = converter.convertToBoolean(f);
        assertNotNull(b);
        assertFalse(b);

        b = converter.convertToBoolean(Ttrue);
        assertNotNull(b);
        assertTrue(b);

        b = converter.convertToBoolean(Ftrue);
        assertNotNull(b);
        assertFalse(b);

        b = converter.convertToBoolean(tTrue);
        assertNotNull(b);
        assertTrue(b);

        b = converter.convertToBoolean(fFalse);
        assertNotNull(b);
        assertFalse(b);

        b = converter.convertToBoolean(aTrue);
        assertNotNull(b);
        assertTrue(b);

        b = converter.convertToBoolean(aFalse);
        assertNotNull(b);
        assertFalse(b);

    }

    @Test
    public void convertToBoolean_Null() throws Exception {
        String s = null;
        Boolean b = converter.convertToBoolean(s);
        assertNull(b);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToBoolean_Exception() throws Exception {
        String s = "-1";
        Boolean b = converter.convertToBoolean(s);
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToByte() throws Exception {
        String s = "0";
        Byte b = converter.convertToByte(s);
    }

    @Test
    public void convertToByte_Null() throws Exception {
        assertNull(converter.convertToByte(null));
    }

    @Test
    public void convertToCharacter() throws Exception {
        String s = "0";
        Character c = converter.convertToCharacter(s);
        assertNotNull(c);
        assertEquals(48, c.charValue());
    }

    @Test
    public void convertToCharacter_Null() throws Exception {
        String s = null;
        Character c = converter.convertToCharacter(s);
        assertNull(c);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToCharacter_EmptyString() throws Exception {
        String s = "";
        Character c = converter.convertToCharacter(s);
    }

    @Test
    public void convertToCharacter_StringSpace() throws Exception {
        String s = " ";
        Character c = converter.convertToCharacter(s);
        assertNotNull(c);
        assertEquals(32, c.charValue());
    }

    @Test
    public void convertToDouble() throws Exception {
        String s = "0.0";
        Double d = converter.convertToDouble(s);
        assertNotNull(d);
        assertEquals(0.0, d, 0.0);
    }

    @Test
    public void convertToDouble_Null() throws Exception {
        String s = null;
        Double d = converter.convertToDouble(s);
        assertNull(d);
    }

    @Test
    public void convertToDouble_MAX() throws Exception {
        String s = String.valueOf(Double.MAX_VALUE);
        Double d = converter.convertToDouble(s);
        assertNotNull(d);
        assertEquals(Double.MAX_VALUE, d, 0.0);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToDouble_GreaterThanMAX() throws Exception {
        String s = "1.7976931348623157E400";
        Double d = converter.convertToDouble(s);
    }

    @Test
    public void convertToDouble_LessThanMIN() throws Exception {
        String s = "-4.9E-325";
        Double d = converter.convertToDouble(s);
        assertNotNull(d);
        assertEquals(0.0, d, 0.0);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToDouble_Unparseable() throws Exception {
        String s = "1.2efff";
        Double d = converter.convertToDouble(s);
    }

    @Test
    public void convertToFloat() throws Exception {
        String s = "0";
        Float f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(0.0, f, 0.0);
        s = "1";
        f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(1.0, f, 0.0);
    }

    @Test
    public void convertToFloat_Null() throws Exception {
        assertNull(converter.convertToFloat(null));
    }

    @Test
    public void convertToFloat_MAX() throws Exception {
        String s = "3.4028235E38";
        Float f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(Float.MAX_VALUE, f, 0.0);
    }

    @Test
    public void convertToFloat_MIN() throws Exception {
        String s = "1.401298464324817E-45";
        Float f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(Float.MIN_VALUE, f, 0.0);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToFloat_Unparsable() throws Exception {
        String s = "QWERTY";
        Float f = converter.convertToFloat(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToFloat_GreaterThanMAX() throws Exception {
        String s = "3.4028235E39";
        Float f = converter.convertToFloat(s);
    }

    @Test
    public void convertToFloat_LessThanMIN() throws Exception {
        String s = "1.4E-49";
        Float f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(0.0, f, 0.0);
    }


    @Test
    public void convertToInteger() throws Exception {
        String s = "0";
        Integer i = converter.convertToInteger(s);
        assertNotNull(i);
        assertEquals(0, i, 0.0);
    }

    @Test
    public void convertToInteger_Null() throws Exception {
        String s = null;
        Integer i = converter.convertToInteger(s);
        assertNull(i);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToInteger_LessThanMIN() throws Exception {
        String s = "-21474836495554545";
        Integer i = converter.convertToInteger(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToInteger_GreaterThanMAX() throws Exception {
        System.out.println(Integer.MAX_VALUE);
        String s = "214748364755545422145221";
        Integer i = converter.convertToInteger(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToInteger_Unparseable() throws Exception {
        String s = "2147483648qwerty";
        Integer i = converter.convertToInteger(s);
    }

    @Test
    public void convertToLong() throws Exception {
        String s = "1";
        Long l = converter.convertToLong(s);
        assertNotNull(l);
        assertEquals(1, l.longValue(), 0.0);
    }

    @Test
    public void convertToLong_Null() throws Exception {
        String s = null;
        Long l = converter.convertToLong(s);
        assertNull(l);
    }

    @Test
    public void convertToLong_MAX() throws Exception {
        String s = String.valueOf(Long.MAX_VALUE);
        Long l = converter.convertToLong(s);
        assertNotNull(l);
        assertEquals(Long.MAX_VALUE, l, 0.0);
    }

    @Test
    public void convertToLong_MIN() throws Exception {
        String s = String.valueOf(Long.MIN_VALUE);
        Long l = converter.convertToLong(s);
        assertNotNull(l);
        assertEquals(Long.MIN_VALUE, l, 0.0);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToLong_GreaterThanMAX() throws Exception {
        String s = "9223372036854775808";
        Long l = converter.convertToLong(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToLong_LessThanMIN() throws Exception {
        String s = "-9223372036854775809";
        Long l = converter.convertToLong(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToLong_Unparsable() throws Exception {
        String s = "QWERTY";
        Long l = converter.convertToLong(s);
    }

    @Test
    public void convertToShort() throws Exception {
        String aString = "0";
        Short s = converter.convertToShort(aString);
        assertNotNull(s);
        assertEquals(0, s, 0.0);
    }

    @Test
    public void convertToShort_Null() throws Exception {
        String aString = null;
        Short s = converter.convertToShort(aString);
        assertNull(s);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToShort_Unparsable() throws Exception {
        String s = "QWERTY";
        Short aShort = converter.convertToShort(s);
    }


    @Test
    public void convertToString() throws Exception {
        String S = "0";
        String s = converter.convertToString(S);
        assertNotNull(s);
        assertNotSame(s, S);
        assertTrue("0".equals(s));
    }

    @Test
    public void convertToString_Null() throws Exception {
        String aString = null;
        String s = converter.convertToString(aString);
        assertNull(s);
    }

}
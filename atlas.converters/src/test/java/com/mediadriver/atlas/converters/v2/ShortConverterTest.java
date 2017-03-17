package com.mediadriver.atlas.converters.v2;

import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 3/7/17.
 */

public class ShortConverterTest {
    private ShortConverter converter = new ShortConverter();

    @Test
    public void convertToBoolean() throws Exception {
        Short f = 0;
        Short t = 1;

        Boolean b = converter.convertToBoolean(t);
        assertNotNull(b);
        assertTrue(b);

        b = converter.convertToBoolean(f);
        assertNotNull(b);
        assertFalse(b);

    }

    @Test
    public void convertToBoolean_Null() throws Exception {
        Short l = null;
        Boolean b = converter.convertToBoolean(l);
        assertNull(b);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToBoolean_Exception() throws Exception {
        Short dt = -1;
        Boolean b = converter.convertToBoolean(dt);
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToByte() throws Exception {
        Short l = 0;
        Byte b = converter.convertToByte(l);
    }

    @Test
    public void convertToByte_Null() throws Exception {
        assertNull(converter.convertToByte(null));
    }

    @Test
    public void convertToCharacter() throws Exception {
        Short s = 0;
        Character c = converter.convertToCharacter(s);
        assertNotNull(c);
        assertEquals(0, c.charValue());
    }

    @Test
    public void convertToCharacter_Null() throws Exception {
        Short s = null;
        Character c = converter.convertToCharacter(s);
        assertNull(c);
    }

    @Test
    public void convertToCharacter_MAX() throws Exception {
        Short s = Short.MAX_VALUE;
        Character c = converter.convertToCharacter(s);
        assertNotNull(c);
        assertEquals(32767, c.charValue());
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToCharacter_MIN() throws Exception {
        Short s = Short.MIN_VALUE;
        Character c = converter.convertToCharacter(s);
    }


    @Test
    public void convertToDouble() throws Exception {
        Short s = 0;
        Double d = converter.convertToDouble(s);
        assertNotNull(d);
        assertEquals(0.0, d, 0.0);
    }

    @Test
    public void convertToDouble_Null() throws Exception {
        Short s = null;
        Double d = converter.convertToDouble(s);
        assertNull(d);
    }

    @Test
    public void convertToDouble_MAX() throws Exception {
        Short s = Short.MAX_VALUE;
        Double d = converter.convertToDouble(s);
        assertNotNull(d);
        assertEquals(Short.MAX_VALUE, s, 0.0);
    }


    @Test
    public void convertToFloat() throws Exception {
        Short s = 0;
        Float f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(0.0f, f, 0.0);
    }

    @Test
    public void convertToFloat_Null() throws Exception {
        assertNull(converter.convertToFloat(null));
    }

    @Test
    public void convertToFloat_MAX() throws Exception {
        Short s = Short.MAX_VALUE;
        Float f = converter.convertToFloat(s);
        assertNotNull(f);
        assertEquals(Short.MAX_VALUE, s, 0.0);
    }

    @Test
    public void convertToInteger() throws Exception {
        Short s = 0;
        Integer i = converter.convertToInteger(s);
        assertNotNull(i);
        assertEquals(0, i, 0.0);
    }

    @Test
    public void convertToInteger_Null() throws Exception {
        Short l = null;
        Integer i = converter.convertToInteger(l);
        assertNull(i);
    }

    @Test
    public void convertToLong() throws Exception {
        Short s = 1;
        Long l = converter.convertToLong(s);
        assertNotNull(l);
        assertEquals(1, l, 0.0);
    }

    @Test
    public void convertToLong_Null() throws Exception {
        Short s = null;
        Long l = converter.convertToLong(s);
        assertNull(l);
    }

    @Test
    public void convertToLong_MAX() throws Exception {
        Short s = Short.MAX_VALUE;
        Long l = converter.convertToLong(s);
        assertNotNull(l);
        assertEquals(32767.0, l, 0.0);
    }

    @Test
    public void convertToLong_MIN() throws Exception {
        Short s = Short.MIN_VALUE;
        Long l = converter.convertToLong(s);
        assertNotNull(l);
        assertEquals(-32768.0, l, 0.0);
    }

    @Test
    public void convertToShort() throws Exception {
        Short aShort = 0;
        Short s = converter.convertToShort(aShort);
        assertNotNull(s);
        assertNotSame(aShort, s);
        assertEquals(0, s, 0.0);
    }

    @Test
    public void convertToShort_Null() throws Exception {
        Short l = null;
        Short s = converter.convertToShort(l);
        assertNull(s);
    }


    @Test
    public void convertToString() throws Exception {
        Short l = 0;
        String s = converter.convertToString(l);
        assertNotNull(s);
        assertTrue("0".equals(s));
    }

    @Test
    public void convertToString_Null() throws Exception {
        Short l = null;
        String s = converter.convertToString(l);
        assertNull(s);
    }

}
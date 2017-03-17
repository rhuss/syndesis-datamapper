package com.mediadriver.atlas.converters.v2;

import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by on 3/6/17.
 */
public class IntegerConverterTest {

    private IntegerConverter converter = new IntegerConverter();


    @Test
    public void convertToBoolean() throws Exception {
        int xTrue = 1;
        int xFalse = 0;

        Boolean out = converter.convertToBoolean(xTrue);
        assertNotNull(out);
        assertTrue(out);
        out = converter.convertToBoolean(xFalse);
        assertNotNull(out);
        assertFalse(out);
    }

    @Test
    public void convertToBoolean_Null() throws Exception {
        Boolean out = converter.convertToBoolean(null);
        assertNull(out);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToBoolean_ConvertException() throws Exception {
        Boolean out = converter.convertToBoolean(10);
        fail();
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToByte() throws Exception {
        Byte byt = converter.convertToByte(Integer.MAX_VALUE);
        fail();
    }

    @Test
    public void convertToByte_Null() throws Exception {
        Byte byt = converter.convertToByte(null);
        assertNull(byt);
    }

    @Test
    public void convertToCharacter_Null() throws Exception {
        Character character = converter.convertToCharacter(null);
        assertNull(character);
    }

    @Test
    public void convertToCharacter() throws Exception {
        Integer integer = 0;
        Character character = converter.convertToCharacter(integer);
        assertNotNull(character);
        int i = Character.codePointAt(String.valueOf(character), 0);
        assertEquals(integer.intValue(), i);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToCharacter_GreaterThanMAX() throws Exception {
        Integer integer = 1500000;
        Character character = converter.convertToCharacter(integer);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToCharacter_LessThanMIN() throws Exception {
        Integer integer = -1500000;
        Character character = converter.convertToCharacter(integer);
    }

    @Test
    public void convertToDouble() throws Exception {
        Integer integer = 0;
        Double d = converter.convertToDouble(integer);
        assertNotNull(d);
        assertEquals(d, 0.0, 0.0);

        integer = 1;
        d = converter.convertToDouble(integer);
        assertNotNull(d);
        assertEquals(1.0, d, 0.0);
    }

    @Test
    public void convertToDouble_Null() throws Exception {
        assertNull(converter.convertToDouble(null));
    }

    @Test
    public void convertToFloat() throws Exception {
        Integer integer = 0;
        Float f = converter.convertToFloat(integer);
        assertNotNull(f);
        assertEquals(f, 0.0, 0.0);

        integer = 1;
        f = converter.convertToFloat(integer);
        assertNotNull(f);
        assertEquals(1.0, f, 0.0);
    }

    @Test
    public void convertToFloat_Null() throws Exception {
        assertNull(converter.convertToFloat(null));
    }


    @Test
    public void convertToShort() throws Exception {
        int i = Short.MAX_VALUE;
        int negI = Short.MIN_VALUE;

        Short out = converter.convertToShort(i);
        assertNotNull(out);
        assertEquals(i, out.intValue());
        out = converter.convertToShort(negI);
        assertNotNull(out);
        assertEquals(negI, out.intValue());
    }

    @Test
    public void convertToShort_Null() throws Exception {
        Short out = converter.convertToShort(null);
        assertNull(out);
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToShort_ConvertExceptionGreaterThanMax() throws Exception {
        Short out = converter.convertToShort(Integer.MAX_VALUE);
        fail();
    }

    @Test(expected = AtlasConversionException.class)
    public void convertToShort_ConvertExceptionLessThanMin() throws Exception {
        Short out = converter.convertToShort(Integer.MIN_VALUE);
        fail();
    }

    @Test
    public void convertToLong() throws Exception {
        int i = Integer.MAX_VALUE;
        int negI = Integer.MIN_VALUE;

        Long out = converter.convertToLong(i);
        assertNotNull(out);
        assertEquals(i, out.intValue());
        out = converter.convertToLong(negI);
        assertNotNull(out);
        assertEquals(negI, out.intValue());
    }

    @Test
    public void convertToLong_Null() throws Exception {
        Long out = converter.convertToLong(null);
        assertNull(out);
    }

    @Test
    public void convertToString() throws Exception {
        int i = Integer.MAX_VALUE;
        int negI = Integer.MIN_VALUE;

        String out = converter.convertToString(i);
        assertNotNull(out);
        assertEquals(Integer.toString(i), out);
        out = converter.convertToString(negI);
        assertNotNull(out);
        assertEquals(Integer.toString(negI), out);
    }

    @Test
    public void convertToString_Null() throws Exception {
        String out = converter.convertToString(null);
        assertNull(out);
    }

    @Test
    public void convertToInteger() throws Exception {
        Integer foo = Integer.MAX_VALUE;
        Integer out = converter.convertToInteger(foo);
        foo++;
        assertNotNull(out);
        // test that a copy was made
        assertNotSame(out, foo);
        assertNotEquals(out, foo);
    }

    @Test
    public void convertToInteger_Null() throws Exception {
        Integer out = converter.convertToInteger(null);
        assertNull(out);
    }


}
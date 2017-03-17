package com.mediadriver.atlas.converters.v2;

import com.mediadriver.atlas.api.v2.AtlasUnsupportedException;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created  on 3/7/17.
 */
public class ByteConverterTest {

    private ByteConverter byteConverter = new ByteConverter();

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToBoolean() throws Exception {
        byteConverter.convertToBoolean(Byte.MAX_VALUE);
    }

    @Test
    public void convertToBoolean_Null() throws Exception {
        assertNull(byteConverter.convertToBoolean(null));
    }


    @Test(expected = AtlasUnsupportedException.class)
    public void convertToByte() throws Exception {
        byteConverter.convertToByte(Byte.MAX_VALUE);
    }

    @Test
    public void convertToByte_Null() throws Exception {
        assertNull(byteConverter.convertToByte(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToCharacter() throws Exception {
        byteConverter.convertToCharacter(Byte.MAX_VALUE);
    }

    @Test
    public void convertToCharacter_Null() throws Exception {
        assertNull(byteConverter.convertToCharacter(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToDouble() throws Exception {
        byteConverter.convertToDouble(Byte.MAX_VALUE);
    }

    @Test
    public void convertToDouble_Null() throws Exception {
        assertNull(byteConverter.convertToDouble(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToFloat() throws Exception {
        byteConverter.convertToFloat(Byte.MAX_VALUE);
    }

    @Test
    public void convertToFloat_Null() throws Exception {
        assertNull(byteConverter.convertToFloat(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToInteger() throws Exception {
        byteConverter.convertToInteger(Byte.MAX_VALUE);
    }

    @Test
    public void convertToInteger_Null() throws Exception {
        assertNull(byteConverter.convertToInteger(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToLong() throws Exception {
        byteConverter.convertToLong(Byte.MAX_VALUE);
    }

    @Test
    public void convertToLong_Null() throws Exception {
        assertNull(byteConverter.convertToLong(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToShort() throws Exception {
        byteConverter.convertToShort(Byte.MAX_VALUE);
    }

    @Test
    public void convertToIShort_Null() throws Exception {
        assertNull(byteConverter.convertToShort(null));
    }

    @Test(expected = AtlasUnsupportedException.class)
    public void convertToString() throws Exception {
        byteConverter.convertToString(Byte.MAX_VALUE);
    }

    @Test
    public void convertToString_Null() throws Exception {
        assertNull(byteConverter.convertToString(null));
    }

}
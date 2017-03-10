package com.mediadriver.atlas.java.inspect.v2;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testRemoveGetterAndLowercaseFirstLetter() {
		assertNull(StringUtil.removeGetterAndLowercaseFirstLetter(null));
		assertEquals("", StringUtil.removeGetterAndLowercaseFirstLetter(""));
		assertEquals("g", StringUtil.removeGetterAndLowercaseFirstLetter("g"));
		assertEquals("ge", StringUtil.removeGetterAndLowercaseFirstLetter("ge"));
		assertEquals("get", StringUtil.removeGetterAndLowercaseFirstLetter("get"));
		assertEquals("i", StringUtil.removeGetterAndLowercaseFirstLetter("i"));
		assertEquals("is", StringUtil.removeGetterAndLowercaseFirstLetter("is"));
		assertEquals("abc", StringUtil.removeGetterAndLowercaseFirstLetter("getAbc"));
		assertEquals("abc", StringUtil.removeGetterAndLowercaseFirstLetter("isAbc"));
	}

}

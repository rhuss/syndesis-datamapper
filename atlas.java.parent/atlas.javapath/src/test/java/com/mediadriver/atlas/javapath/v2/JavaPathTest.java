package com.mediadriver.atlas.javapath.v2;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaPathTest {	
	
	@Test
	public void testOneClass() {
		JavaPath foo = new JavaPath();
		foo.appendField("user");
		assertEquals("user", foo.toString());
	}

	
	@Test
	public void testFields() {
		JavaPath foo = new JavaPath();
		foo.appendField("user").appendField("name");
		assertEquals("user.name", foo.toString());
		foo.appendField("bar");
		assertEquals("user.name.bar", foo.toString());
	}
	
}

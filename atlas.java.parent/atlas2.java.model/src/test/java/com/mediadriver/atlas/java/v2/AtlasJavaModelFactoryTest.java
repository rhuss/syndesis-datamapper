package com.mediadriver.atlas.java.v2;

import static org.junit.Assert.*;
import org.junit.Test;

public class AtlasJavaModelFactoryTest {

	@Test
	public void testCreateJavaClass() {
		JavaClass javaClass = AtlasJavaModelFactory.createJavaClass();
		assertNotNull(javaClass);
		assertNotNull(javaClass.getJavaFields());
		assertNotNull(javaClass.getJavaFields().getJavaField());
		assertEquals(new Integer(0), new Integer(javaClass.getJavaFields().getJavaField().size()));
		assertNotNull(javaClass.getJavaEnumFields());
		assertNotNull(javaClass.getJavaEnumFields().getJavaEnumField());
		assertEquals(new Integer(0), new Integer(javaClass.getJavaEnumFields().getJavaEnumField().size()));
	}

}

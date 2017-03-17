package com.mediadriver.atlas.java.inspect.v2;

import static org.junit.Assert.*;

import com.mediadriver.atlas.java.v2.AtlasJavaModelFactory;
import com.mediadriver.atlas.java.v2.JavaClass;

public class PrimitiveValidationUtil {

	public static void validatePrimitive(JavaClass j, String name) {
		validatePrimitiveCommon(j);
		assertEquals(name, j.getClassName());
		assertEquals(name, j.getFullyQualifiedName());
		assertFalse(j.isArray());
		assertNull(j.getArrayDimensions());
	}
	
	public static void validatePrimitiveArray(JavaClass j, String name, int dim) {
		validatePrimitiveCommon(j);
		assertEquals(name, j.getClassName());
		assertEquals(name, j.getFullyQualifiedName());
		assertTrue(j.isArray());
		assertEquals(new Integer(dim), j.getArrayDimensions());
	}
	
	protected static void validatePrimitiveCommon(JavaClass j) {
		assertNotNull(j);
		assertFalse(j.isAnnonymous());
		assertFalse(j.isAnnotation());
		assertFalse(j.isEnumeration());
		assertFalse(j.isInterface());
		assertFalse(j.isLocalClass());
		assertFalse(j.isMemberClass());
		assertTrue(j.isPrimitive());
		assertFalse(j.isSynthetic());
		assertNotNull(j.getJavaFields());
		assertNotNull(j.getJavaFields().getJavaField());
		assertTrue(j.getJavaFields().getJavaField().size() == 0);
		assertNotNull(j.getJavaEnumFields());
		assertNotNull(j.getJavaEnumFields().getJavaEnumField());
		assertTrue(j.getJavaEnumFields().getJavaEnumField().size() == 0);
		assertNull(j.getPackageName());
		assertNotNull(j.getUri());
		assertEquals(String.format(AtlasJavaModelFactory.URI_FORMAT, j.getFullyQualifiedName()), j.getUri());
	}
}

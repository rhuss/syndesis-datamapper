package com.mediadriver.atlas.java.inspect.v2;

import static org.junit.Assert.*;
import org.junit.Test;

import com.mediadriver.atlas.java.test.v2.CachedComplexClass;
import com.mediadriver.atlas.java.v2.AtlasJavaModelFactory;
import com.mediadriver.atlas.java.v2.JavaClass;
import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.FieldStatus;

public class CachedComplexClassInspectClassTest {

	@Test
	public void testCachedComplexClass() throws Exception {
		JavaClass c = ClassInspector.inspectClass(CachedComplexClass.class);
		assertNotNull(c);
		assertNull(c.getAnnotations());
		assertNull(c.getArrayDimensions());
		assertEquals("CachedComplexClass", c.getClassName());
		assertEquals("com.mediadriver.atlas.java.test.v2.CachedComplexClass", c.getFullyQualifiedName());
		assertNull(c.getGetMethod());
		assertNotNull(c.getJavaEnumFields());
		assertNotNull(c.getJavaEnumFields().getJavaEnumField());
		assertTrue(c.getJavaEnumFields().getJavaEnumField().size() == 0);
		assertNotNull(c.getJavaFields());
		assertNotNull(c.getJavaFields().getJavaField());
		assertNull(c.getName());
		assertEquals("com.mediadriver.atlas.java.test.v2", c.getPackageName());
		assertNull(c.getSetMethod());
		assertNull(c.getType());
		assertNotNull(c.getUri());
		assertEquals(String.format(AtlasJavaModelFactory.URI_FORMAT, "com.mediadriver.atlas.java.test.v2.CachedComplexClass"), c.getUri());
		assertNull(c.getValue());
		
		assertEquals(new Integer(3), new Integer(c.getJavaFields().getJavaField().size()));
		
		Integer validated = 0;
		for(JavaField f : c.getJavaFields().getJavaField()) {
			if("com.mediadriver.atlas.java.test.v2.TestOrder".equals(f.getClassName())) {
				//ClassValidationUtil.validateSimpleTestContact((JavaClass)f);
				validated++;
			}
			if("com.mediadriver.atlas.java.test.v2.TestContact".equals(f.getClassName())) {
				if(!FieldStatus.CACHED.equals(f.getStatus())) {
					ClassValidationUtil.validateSimpleTestContact((JavaClass)f);
				}
				validated++;
			}
			if("com.mediadriver.atlas.java.test.v2.TestAddress".equals(f.getClassName())) {
				if(!FieldStatus.CACHED.equals(f.getStatus())) {
					ClassValidationUtil.validateSimpleTestAddress((JavaClass)f);
				}
				validated++;
			}
			if("long".equals(f.getClassName())) {
				ClassValidationUtil.validateSerialVersionUID(f);
				validated++;
			}		
		}
		
		assertEquals(validated, new Integer(c.getJavaFields().getJavaField().size()));
	}

}

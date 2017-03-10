package com.mediadriver.atlas.java.inspect.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.mediadriver.atlas.java.v2.JavaClass;
import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.Field;
import com.mediadriver.atlas.v2.FieldStatus;
import com.mediadriver.atlas.v2.FieldType;

public class ClassValidationUtil {
	
	public static void validateFlatPrimitiveClass(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveClass", flatClass.getClassName());
		assertFalse(flatClass.isArray());
		assertEquals(null, flatClass.getArrayDimensions());
		assertFalse(flatClass.isInterface());		
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveClass", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveClassArray(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveClass", flatClass.getClassName());
		assertTrue(flatClass.isArray());
		assertEquals(new Integer(1), flatClass.getArrayDimensions());
		assertFalse(flatClass.isInterface());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveClass", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveClassTwoDimArray(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveClass", flatClass.getClassName());
		assertTrue(flatClass.isArray());
		assertEquals(new Integer(2), flatClass.getArrayDimensions());
		assertFalse(flatClass.isInterface());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveClass", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveClassThreeDimArray(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveClass", flatClass.getClassName());
		assertTrue(flatClass.isArray());
		assertEquals(new Integer(3), flatClass.getArrayDimensions());
		assertFalse(flatClass.isInterface());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveClass", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveInterface(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveInterface", flatClass.getClassName());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveInterface", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveInterfaceArray(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveInterface", flatClass.getClassName());
		assertTrue(flatClass.isArray());
		assertEquals(new Integer(1), flatClass.getArrayDimensions());
		assertTrue(flatClass.isInterface());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveInterface", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveInterfaceTwoDimArray(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveInterface", flatClass.getClassName());
		assertTrue(flatClass.isArray());
		assertEquals(new Integer(2), flatClass.getArrayDimensions());
		assertTrue(flatClass.isInterface());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveInterface", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatPrimitiveInterfaceThreeDimArray(Class<?> clazz) throws Exception {
		JavaClass flatClass = ClassInspector.inspectClass(clazz);
		validateFlatClass(flatClass);
		assertEquals("FlatPrimitiveInterface", flatClass.getClassName());
		assertTrue(flatClass.isArray());
		assertEquals(new Integer(3), flatClass.getArrayDimensions());
		assertTrue(flatClass.isInterface());
		assertEquals("com.mediadriver.atlas.java.test.v2.FlatPrimitiveInterface", flatClass.getFullyQualifiedName());
		validateFlatPrimitiveFields(flatClass);
	}
	
	public static void validateFlatClass(JavaClass flatClass) {
		assertNotNull(flatClass);
		assertNotNull(flatClass.getClassName());
		assertNotNull(flatClass.getFullyQualifiedName());
		assertFalse(flatClass.isAnnonymous());
		assertFalse(flatClass.isEnumeration());
		assertFalse(flatClass.isLocalClass());
		assertFalse(flatClass.isMemberClass());
		assertFalse(flatClass.isPrimitive());
		assertFalse(flatClass.isSynthetic());
	}
	
	public static void validateFlatPrimitiveFields(JavaClass flatClass) throws Exception {
		assertNotNull(flatClass.getPackageName());
		assertEquals("com.mediadriver.atlas.java.test.v2", flatClass.getPackageName());
		assertNotNull(flatClass.getJavaFields());
		assertNotNull(flatClass.getJavaFields().getJavaField());
		assertFalse(flatClass.getJavaFields().getJavaField().isEmpty());
		assertEquals(new Integer(34), new Integer(flatClass.getJavaFields().getJavaField().size()));
		assertNotNull(flatClass.getJavaEnumFields());
		assertNotNull(flatClass.getJavaEnumFields().getJavaEnumField());
		assertTrue(flatClass.getJavaEnumFields().getJavaEnumField().isEmpty());
		
		for(JavaField f : flatClass.getJavaFields().getJavaField()) {
			assertNotNull(f);
			assertTrue(f instanceof JavaField);
			JavaField j = (JavaField)f;
			assertNotNull(j.getName());
			switch(j.getType()) {
			case BOOLEAN: 
				if(j.isArray()) {
					validatePrimitiveField("boolean", "Boolean", j, false);
				} else {
					validatePrimitiveField("boolean", "Boolean", j, true);
				}
				break;
			case BYTE:  
				validatePrimitiveField("byte", "Byte", j);
				break;
			case CHAR:  
				validatePrimitiveField("char", "Char", j);
				break;
			case DOUBLE:  
				validatePrimitiveField("double", "Double", j);
				break;
			case FLOAT:  
				validatePrimitiveField("float", "Float", j);
				break;
			case INTEGER:  
				validatePrimitiveField("int", "Int", j);
				break;
			case LONG:  
				validatePrimitiveField("long", "Long", j);
				break;
			case SHORT:  
				validatePrimitiveField("short", "Short", j);
				break;
			case STRING:  
				validatePrimitiveField("string", "String", j);
				break;
			default: fail("Extra field detected: " + j.getName());
			}			
		}
	}
	
	public static void validatePrimitiveField(String lowName, String capName, JavaField j) {
		validatePrimitiveField(lowName, capName, j, false);
	}
	
	public static void validatePrimitiveField(String lowName, String capName, JavaField j, boolean usesIs) {
		assertNotNull("Field: " + j.getName(), j.getGetMethod());
		assertNotNull("Field: " + j.getName(), j.getSetMethod());
		assertNotNull("Field: " + j.getName(), j.getType());
		assertNull("Field: " + j.getName(), j.getValue());
		assertNull("Field: " + j.getName(), j.getAnnotations());
		
		assertEquals(FieldStatus.SUPPORTED, j.getStatus());
		//assertEquals(isArray, j.isArray());
		// TODO: Support for collections
		assertNull(j.isCollection());
		assertTrue(j.isPrimitive());
		assertFalse(j.isSynthetic());
		
		String fieldText = "Field";
		if(j.isArray()) {
			fieldText = "ArrayField";
			assertEquals(new Integer(1), j.getArrayDimensions());
		}
		
		if(String.format("%s%s", lowName, fieldText).equals(j.getName())) {
			if(usesIs) {
				assertEquals(String.format("is%s%s", capName, fieldText), j.getGetMethod());
			} else {
				assertEquals(String.format("get%s%s", capName, fieldText), j.getGetMethod());
			}
			assertEquals(String.format("set%s%s", capName, fieldText), j.getSetMethod());
		} else if(String.format("boxed%s%s", capName, fieldText).equals(j.getName())) {
			assertEquals(String.format("getBoxed%s%s", capName, fieldText), j.getGetMethod());
			assertEquals(String.format("setBoxed%s%s", capName, fieldText), j.getSetMethod());
		} else {
			fail("Extra field detected: " + j.getName());
		}
	}
	
	public static void validateSimpleTestContact(JavaClass c) {
		assertNotNull(c);
		assertFalse(c.isAnnonymous());
		assertFalse(c.isAnnotation());
		assertFalse(c.isArray());
		assertTrue(c.isCollection() == null || c.isCollection());
		assertFalse(c.isEnumeration());
		assertFalse(c.isInterface());
		assertFalse(c.isLocalClass());
		assertFalse(c.isMemberClass());
		assertFalse(c.isPrimitive());
		assertFalse(c.isSynthetic());
		assertEquals("com.mediadriver.atlas.java.test.v2.TestContact", c.getClassName());
		assertEquals("com.mediadriver.atlas.java.test.v2", c.getPackageName());
		assertEquals("com.mediadriver.atlas.java.test.v2.TestContact", c.getFullyQualifiedName());
		assertNotNull(c.getJavaEnumFields());
		assertNotNull(c.getJavaEnumFields().getJavaEnumField());
		assertEquals(new Integer(0), new Integer(c.getJavaEnumFields().getJavaEnumField().size()));
		assertNotNull(c.getJavaFields());
		assertNotNull(c.getJavaFields().getJavaField());
		assertEquals(new Integer(5), new Integer(c.getJavaFields().getJavaField().size()));
		
		for(JavaField f : c.getJavaFields().getJavaField()) {
			switch (f.getName()) {
			case "serialVersionUID": validateSerialVersionUID(f); break;
			case "firstName": break;
			case "lastName": break;
			case "phoneNumber": break;
			case "zipCode": break;
			default: fail("Unexpected field detected: " + f.getName());
			}
		}
	}
	
	public static void validateSimpleTestAddress(JavaClass c) {
		assertNotNull(c);
		assertFalse(c.isAnnonymous());
		assertFalse(c.isAnnotation());
		assertFalse(c.isArray());
		assertTrue(c.isCollection() == null || c.isCollection());
		assertFalse(c.isEnumeration());
		assertFalse(c.isInterface());
		assertFalse(c.isLocalClass());
		assertFalse(c.isMemberClass());
		assertFalse(c.isPrimitive());
		assertFalse(c.isSynthetic());
		assertEquals("com.mediadriver.atlas.java.test.v2.TestAddress", c.getClassName());
		assertEquals("com.mediadriver.atlas.java.test.v2", c.getPackageName());
		assertEquals("com.mediadriver.atlas.java.test.v2.TestAddress", c.getFullyQualifiedName());
		assertNotNull(c.getJavaEnumFields());
		assertNotNull(c.getJavaEnumFields().getJavaEnumField());
		assertEquals(new Integer(0), new Integer(c.getJavaEnumFields().getJavaEnumField().size()));
		assertNotNull(c.getJavaFields());
		assertNotNull(c.getJavaFields().getJavaField());
		assertEquals(new Integer(6), new Integer(c.getJavaFields().getJavaField().size()));
		
		for(JavaField f : c.getJavaFields().getJavaField()) {
			switch (f.getName()) {
			case "serialVersionUID": validateSerialVersionUID(f); break;
			case "addressLine1": break;
			case "addressLine2": break;
			case "city": break;
			case "state": break;
			case "zipCode": break;
			default: fail("Unexpected field detected: " + f.getName());
			}
		}
	}
	
	public static void validateSerialVersionUID(JavaField f) {
		assertNotNull(f);
		assertEquals("serialVersionUID", f.getName());
		assertEquals("long", f.getClassName());
		assertEquals(FieldType.LONG, f.getType());
		assertEquals(true, f.isPrimitive());
		assertEquals(false, f.isArray());
		assertEquals(false, f.isSynthetic());
	}
}

package com.mediadriver.atlas.java.inspect.v2;

import org.junit.Test;

public class PrimitiveInspectTest {

	@Test
	public void testPrimitives() throws Exception {
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(boolean.class), "boolean");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(byte.class), "byte");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(char.class), "char");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(double.class), "double");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(float.class), "float");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(int.class), "int");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(long.class), "long");
		PrimitiveValidationUtil.validatePrimitive(ClassInspector.inspectClass(short.class), "short");
	}
	
	@Test
	public void testPrimitiveArrays() throws Exception {
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(boolean[].class), "boolean", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(byte[].class), "byte", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(char[].class), "char", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(double[].class), "double", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(float[].class), "float", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(int[].class), "int", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(long[].class), "long", 1);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(short[].class), "short", 1);
	}
	
	@Test
	public void testPrimitiveTwoDimArrays() throws Exception {
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(boolean[][].class), "boolean", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(byte[][].class), "byte", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(char[][].class), "char", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(double[][].class), "double", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(float[][].class), "float", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(int[][].class), "int", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(long[][].class), "long", 2);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(short[][].class), "short", 2);
	}
	
	@Test
	public void testPrimitiveThreeDimArrays() throws Exception {
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(boolean[][][].class), "boolean", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(byte[][][].class), "byte", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(char[][][].class), "char", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(double[][][].class), "double", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(float[][][].class), "float", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(int[][][].class), "int", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(long[][][].class), "long", 3);
		PrimitiveValidationUtil.validatePrimitiveArray(ClassInspector.inspectClass(short[][][].class), "short", 3);
	}
}

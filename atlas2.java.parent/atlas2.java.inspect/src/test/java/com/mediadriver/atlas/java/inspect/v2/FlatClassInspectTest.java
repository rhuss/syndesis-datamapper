package com.mediadriver.atlas.java.inspect.v2;

import org.junit.Test;
import com.mediadriver.atlas.java.test.v2.FlatPrimitiveClass;
import com.mediadriver.atlas.java.test.v2.FlatPrimitiveInterface;

public class FlatClassInspectTest {

	@Test
	public void testFlatPrimitiveClass() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveClass(FlatPrimitiveClass.class);
	}

	@Test
	public void testFlatPrimitiveClassArray() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveClassArray(FlatPrimitiveClass[].class);
	}
	
	@Test
	public void testFlatPrimitiveClassTwoDimArray() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveClassTwoDimArray(FlatPrimitiveClass[][].class);
	}
	
	@Test
	public void testFlatPrimitiveClassThreeDimArray() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveClassThreeDimArray(FlatPrimitiveClass[][][].class);
	}
	
	@Test
	public void testFlatPrimitiveInterface() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveInterface(FlatPrimitiveInterface.class);
	}
	
	@Test
	public void testFlatPrimitiveInterfaceArray() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveInterfaceArray(FlatPrimitiveInterface[].class);
	}
	
	@Test
	public void testFlatPrimitiveInterfaceTwoDimArray() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveInterfaceTwoDimArray(FlatPrimitiveInterface[][].class);
	}
	
	@Test
	public void testFlatPrimitiveInterfaceThreeDimArray() throws Exception {
		ClassValidationUtil.validateFlatPrimitiveInterfaceThreeDimArray(FlatPrimitiveInterface[][][].class);
	}

}

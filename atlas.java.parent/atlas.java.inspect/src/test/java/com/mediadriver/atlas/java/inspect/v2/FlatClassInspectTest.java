/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

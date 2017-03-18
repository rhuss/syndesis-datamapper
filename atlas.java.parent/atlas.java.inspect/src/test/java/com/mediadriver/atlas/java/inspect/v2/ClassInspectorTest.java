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

import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ClassInspectorTest {

	@Test
	public void testDetectArrayDimensions() {
		assertNull(ClassInspector.detectArrayDimensions(null));
		assertEquals(new Integer(0), ClassInspector.detectArrayDimensions(String.class));
		assertEquals(new Integer(1), ClassInspector.detectArrayDimensions(int[].class));
		assertEquals(new Integer(2), ClassInspector.detectArrayDimensions(String[][].class));
		assertEquals(new Integer(3), ClassInspector.detectArrayDimensions(List[][][].class));
		assertEquals(new Integer(4), ClassInspector.detectArrayDimensions(Map[][][][].class));
		assertEquals(new Integer(64), ClassInspector.detectArrayDimensions(int[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][].class));
		// MAX_DIM_LIMIT NOTE: 255 is the JVM Spec limit
		assertEquals(new Integer(255), 
				ClassInspector.detectArrayDimensions(int[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][][]
														[][][][][][][][][][][][][][][].class));
	}
	
	@Test
	public void testDetectArrayClass() {
		assertNull(ClassInspector.detectArrayClass(null));
		assertEquals(String.class, ClassInspector.detectArrayClass(String.class));
		assertEquals(int.class, ClassInspector.detectArrayClass(int[].class));
		assertEquals(String.class, ClassInspector.detectArrayClass(String[][].class));
		assertEquals(List.class, ClassInspector.detectArrayClass(List[][][].class));
		assertEquals(Map.class, ClassInspector.detectArrayClass(Map[][][][].class));
	}

}

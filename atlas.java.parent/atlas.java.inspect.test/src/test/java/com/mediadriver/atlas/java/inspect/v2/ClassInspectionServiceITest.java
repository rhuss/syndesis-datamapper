package com.mediadriver.atlas.java.inspect.v2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mediadriver.atlas.java.v2.JavaClass;

public class ClassInspectionServiceITest {

	private ClassInspectionService classInspectionService = null;
	
	@Before
	public void setUp() throws Exception {
		classInspectionService = new ClassInspectionService();
	}
	
	@After
	public void tearDown() throws Exception {
		classInspectionService = null;
	}

	@Test
	public void testInspectClassClassNameClassPath() throws InspectionException {
		JavaClass javaClazz = classInspectionService.inspectClass("com.mediadriver.atlas.java.test.v2.FlatPrimitiveClass", "target/reference-jars/atlas.java.test.model-1.10.0-SNAPSHOT.jar");
		assertNotNull(javaClazz);	
	}

}

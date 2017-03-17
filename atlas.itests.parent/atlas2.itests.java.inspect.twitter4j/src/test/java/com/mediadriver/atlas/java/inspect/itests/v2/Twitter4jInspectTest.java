package com.mediadriver.atlas.java.inspect.itests.v2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediadriver.atlas.java.inspect.v2.ClassInspector;
import com.mediadriver.atlas.java.v2.JavaClass;

public class Twitter4jInspectTest {

	private static final Logger logger = LoggerFactory.getLogger(Twitter4jInspectTest.class);
	
	@Test
	public void testInspectTwitter4jStatus() {
		JavaClass j = ClassInspector.inspectClass("twitter4j.Status");
		assertNotNull(j);
		logger.debug("Hello");
	}
	
	@Test
	public void testInspectTwitter4jStatusJSONImpl() {
		JavaClass j = ClassInspector.inspectClass("twitter4j.StatusJSONImpl");
		assertNotNull(j);
		logger.debug("Hello");
	}

}

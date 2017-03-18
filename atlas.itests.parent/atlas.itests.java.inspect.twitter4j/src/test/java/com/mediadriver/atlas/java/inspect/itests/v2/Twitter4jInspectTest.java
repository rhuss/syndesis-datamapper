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

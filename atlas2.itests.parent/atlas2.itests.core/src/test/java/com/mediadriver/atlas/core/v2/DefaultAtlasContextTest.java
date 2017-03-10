package com.mediadriver.atlas.core.v2;

import static org.junit.Assert.*;

import org.junit.Test;

public class DefaultAtlasContextTest {

	@Test
	public void testMockModuleInit() {
		DefaultAtlasContextFactory factory = new DefaultAtlasContextFactory();
		factory.init();
		assertNotNull(factory.getModules());
	}

}

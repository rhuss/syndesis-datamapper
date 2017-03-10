package com.mediadriver.atlas.core.v2;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultAtlasContextFactoryTest {

	private DefaultAtlasContextFactory factory = null;
	private static String THREAD_NAME = null;
	
	@BeforeClass
	public static void beforeClass() {
		THREAD_NAME = Thread.currentThread().getName();
	}
	
	@Test
	public void testInitDestroy() {
		factory = new DefaultAtlasContextFactory();
		factory.init();
		
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));

	}
	
	@Test
	public void testInitDestroyInitDestroy() {
		factory = new DefaultAtlasContextFactory();
		factory.init();
		String origUuid = factory.getUuid();
		
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));
		
		factory.init();
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());	
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertNotEquals(origUuid, factory.getUuid());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));
	}
	
	@Test
	public void testStaticFactoryInitDestroy() {
		factory = DefaultAtlasContextFactory.getInstance();
		assertNotNull(factory);
		assertEquals(THREAD_NAME, factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());	
		assertNotNull(factory.getUuid());
		assertNotNull(factory.getJmxObjectName());
		assertNotNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		
		factory.destroy();
		assertNotNull(factory);
		assertNull(factory.getThreadName());
		assertEquals("com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory", factory.getClassName());
		assertNull(factory.getUuid());
		assertNull(factory.getJmxObjectName());
		assertNull(factory.getMappingService());
		assertNotNull(factory.getModules());
		assertEquals(new Integer(0), new Integer(factory.getModules().size()));
	}
}

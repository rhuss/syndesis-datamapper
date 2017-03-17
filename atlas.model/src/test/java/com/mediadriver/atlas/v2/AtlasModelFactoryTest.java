package com.mediadriver.atlas.v2;

import static org.junit.Assert.*;
import org.junit.Test;

public class AtlasModelFactoryTest {
	
	@Test
	public void testCreateSeparateFieldMapping() {
		SeparateFieldMapping fm = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);
		assertNotNull(fm);
		assertNotNull(fm.getOutputFields());
		assertNotNull(fm.getOutputFields().getMappedField());
		assertNull(fm.getInputField());
		assertEquals(new Integer(0), new Integer(fm.getOutputFields().getMappedField().size()));
	}
	
	@Test
	public void testCreateMapFieldMapping() {
		MapFieldMapping fm = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
		assertNotNull(fm);
		assertNull(fm.getOutputField());
		assertNull(fm.getInputField());
	}
	
	@Test
	public void testCreateCombineFieldMapping() {
		CombineFieldMapping fm = AtlasModelFactory.createFieldMapping(CombineFieldMapping.class);
		assertNotNull(fm);
		assertNotNull(fm.getInputFields());
		assertNotNull(fm.getInputFields().getMappedField());
		assertNull(fm.getOutputField());
		assertEquals(new Integer(0), new Integer(fm.getInputFields().getMappedField().size()));
	}

}

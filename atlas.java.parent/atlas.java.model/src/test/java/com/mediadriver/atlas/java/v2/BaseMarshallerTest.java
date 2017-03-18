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
package com.mediadriver.atlas.java.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.AtlasModelFactory;
import com.mediadriver.atlas.v2.Field;
import com.mediadriver.atlas.v2.FieldActions;
import com.mediadriver.atlas.v2.FieldMapping;
import com.mediadriver.atlas.v2.FieldMappings;
import com.mediadriver.atlas.v2.MapAction;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.MappedFields;
import com.mediadriver.atlas.v2.SeparateFieldMapping;

public abstract class BaseMarshallerTest {

	public boolean deleteTestFolders = true;

	@Rule
	public TestName testName = new TestName();

	@Before
	public void setUp() throws Exception {
		Files.createDirectories(Paths.get("target/junit/" + testName.getMethodName()));
	}

	@After
	public void tearDown() throws Exception {
		if (deleteTestFolders) {
			Path directory = Paths.get("target/junit/" + testName.getMethodName());
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (exc == null) {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					} else {
						throw exc;
					}
				}
			});
		}
	}

	protected AtlasMapping generateAtlasMapping() {
		AtlasMapping mapping = new AtlasMapping();
		mapping.setName("junit");
		mapping.setFieldMappings(new FieldMappings());

		MappedField inputField = new MappedField();
		JavaField inputJavaField = new JavaField();
		inputJavaField.setName("foo");
		inputJavaField.setValue("bar");
		inputField.setField(inputJavaField);

		MappedField outputField = new MappedField();
		JavaField outputJavaField = new JavaField();
		outputJavaField.setName("woot");
		outputJavaField.setValue("blerg");
		outputField.setField(outputJavaField);

		MapFieldMapping fm = new MapFieldMapping();
		fm.setInputField(inputField);
		fm.setOutputField(outputField);

		mapping.getFieldMappings().getFieldMapping().add(fm);
		return mapping;
	}

	protected void validateAtlasMapping(AtlasMapping mapping) {
		assertNotNull(mapping);
		assertNotNull(mapping.getName());
		assertEquals("junit", mapping.getName());
		assertNotNull(mapping.getFieldMappings());
		assertEquals(new Integer(1), new Integer(mapping.getFieldMappings().getFieldMapping().size()));
		assertNull(mapping.getProperties());

		MapFieldMapping fm = (MapFieldMapping)mapping.getFieldMappings().getFieldMapping().get(0);
		assertNotNull(fm);
		assertNull(fm.getAlias());
		
		MappedField m1 = fm.getInputField();
		assertNotNull(m1);
		assertNull(m1.getFieldActions());
		//assertTrue(m1.getFieldActions().isEmpty());
		assertNotNull(m1.getField());
		Field f1 = m1.getField();		
		assertTrue(f1 instanceof JavaField);
		assertEquals("foo", ((JavaField) f1).getName());
		assertEquals("bar", ((JavaField) f1).getValue());
		assertNull(((JavaField) f1).getType());

		MappedField m2 = fm.getOutputField();
		assertNotNull(m2);
		assertNull(m2.getFieldActions());
		//assertTrue(m2.getFieldActions().isEmpty());
		assertNotNull(m2.getField());
		Field f2 = m2.getField();		
		assertTrue(f2 instanceof JavaField);
		assertEquals("woot", ((JavaField) f2).getName());
		assertEquals("blerg", ((JavaField) f2).getValue());
		assertNull(((JavaField) f2).getType());
		
	}
	
	protected AtlasMapping generateSeparateAtlasMapping() {
		AtlasMapping mapping = new AtlasMapping();
		mapping.setName("junit");
		mapping.setFieldMappings(new FieldMappings());

		MappedField inputField = new MappedField();
		JavaField inputJavaField = new JavaField();
		inputJavaField.setName("foo");
		inputJavaField.setValue("bar");
		inputField.setField(inputJavaField);

		MappedField outputFieldA = new MappedField();
		JavaField outputJavaFieldA = new JavaField();
		outputJavaFieldA.setName("woot");
		outputJavaFieldA.setValue("blerg");
		outputFieldA.setField(outputJavaFieldA);
		
		MapAction outputActionA = new MapAction();
		outputActionA.setIndex(new Integer(1));
		outputFieldA.setFieldActions(new FieldActions());
		outputFieldA.getFieldActions().getFieldAction().add(outputActionA);

		MappedField outputFieldB = new MappedField();
		JavaField outputJavaFieldB = new JavaField();
		outputJavaFieldB.setName("meow");
		outputJavaFieldB.setValue("ruff");
		outputFieldB.setField(outputJavaFieldB);
		
		MapAction outputActionB = new MapAction();
		outputActionB.setIndex(new Integer(2));
		outputFieldB.setFieldActions(new FieldActions());
		outputFieldB.getFieldActions().getFieldAction().add(outputActionB);
		
		SeparateFieldMapping fm = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);
		fm.setInputField(inputField);
		fm.getOutputFields().getMappedField().add(outputFieldA);
		fm.getOutputFields().getMappedField().add(outputFieldB);

		mapping.getFieldMappings().getFieldMapping().add(fm);
		return mapping;
	}

	protected void validateSeparateAtlasMapping(AtlasMapping mapping) {
		assertNotNull(mapping);
		assertNotNull(mapping.getName());
		assertEquals("junit", mapping.getName());
		assertNotNull(mapping.getFieldMappings());
		assertEquals(new Integer(1), new Integer(mapping.getFieldMappings().getFieldMapping().size()));
		assertNull(mapping.getProperties());

		FieldMapping fm = mapping.getFieldMappings().getFieldMapping().get(0);
		assertNotNull(fm);
		assertTrue(fm instanceof SeparateFieldMapping);
		assertNull(fm.getAlias());
		
		SeparateFieldMapping sfm = (SeparateFieldMapping)fm;
		MappedField m1 = sfm.getInputField();
		assertNotNull(m1);
		assertNull(m1.getFieldActions());
		//assertEquals(new Integer(0), new Integer(m1.getFieldActions().getFieldAction().size()));
		assertNotNull(m1.getField());
		Field f1 = m1.getField();		
		assertTrue(f1 instanceof JavaField);
		assertEquals("foo", ((JavaField) f1).getName());
		assertEquals("bar", ((JavaField) f1).getValue());
		assertNull(((JavaField) f1).getType());

		MappedFields mFields = sfm.getOutputFields(); 
		MappedField m2 = mFields.getMappedField().get(0);
		assertNotNull(m2);
		assertNotNull(m2.getFieldActions());
		assertEquals(new Integer(1), new Integer(m2.getFieldActions().getFieldAction().size()));
		assertNotNull(m2.getField());
		Field f2 = m2.getField();		
		assertTrue(f2 instanceof JavaField);
		assertEquals("woot", ((JavaField) f2).getName());
		assertEquals("blerg", ((JavaField) f2).getValue());
		assertNull(((JavaField) f2).getType());
		
		MappedField m3 = mFields.getMappedField().get(1);
		assertNotNull(m3);
		assertNotNull(m3.getFieldActions());
		assertEquals(new Integer(1), new Integer(m3.getFieldActions().getFieldAction().size()));
		assertNotNull(m3.getField());
		Field f3 = m3.getField();		
		assertTrue(f3 instanceof JavaField);
		assertEquals("meow", ((JavaField) f3).getName());
		assertEquals("ruff", ((JavaField) f3).getValue());
		assertNull(((JavaField) f3).getType());
		
	}
}

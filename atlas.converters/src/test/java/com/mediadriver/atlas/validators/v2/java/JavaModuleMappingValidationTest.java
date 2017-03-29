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
 **/
package com.mediadriver.atlas.validators.v2.java;

import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.*;
import com.mediadriver.atlas.validators.v2.BaseMappingTest;
import com.mediadriver.atlas.validators.v2.Errors;
import com.mediadriver.atlas.validators.v2.core.AtlasMappingError;
import com.mediadriver.atlas.validators.v2.core.AtlasMappingErrors;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 */
public class JavaModuleMappingValidationTest extends BaseMappingTest {


    @Test
    public void validateAtlasMappingFile_HappyPath() throws Exception {
        AtlasMapping mapping = getMappingFullValid();
        assertNotNull(mapping);
        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_HappyPath2() throws Exception {
        AtlasMapping mapping = getMappingFullValid();
        assertNotNull(mapping);
        Errors mappingErrors = new AtlasMappingErrors();
        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping, mappingErrors);
        validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_LoadFromFile() throws Exception {
        AtlasMapping mapping = mappingUtil.loadMapping("src/test/resources/mappings/HappyPathMapping.xml");
        assertNotNull(mapping);
        Errors mappingErrors = new AtlasMappingErrors();
        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping, mappingErrors);
        validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_InvalidValidatorType() throws Exception {
        AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

        mapping.setName("thisis_a_valid.name");

        mapping.setSourceUri("atlas:java::2");

        mapping.setTargetUri("atlas:java::3");

        FieldMappings fieldMappings = new FieldMappings();

        FieldMapping mapFieldMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
        FieldMapping separateField = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);

        // Mock MappedField
        createMockMappedFields(mapping, fieldMappings, mapFieldMapping);

        MappedField separateInputMappedField = AtlasModelFactory.createMappedField();
        MappedField separateOutMappedField = AtlasModelFactory.createMappedField();

        ((SeparateFieldMapping) separateField).setInputField(separateInputMappedField);

        MockField mockOutField = new MockField();
        MockField mockInField = new MockField();

        separateInputMappedField.setField(mockInField);
        separateOutMappedField.setField(mockOutField);

        ((SeparateFieldMapping) separateField).getOutputFields().getMappedField().add(separateOutMappedField);

        FieldActions fieldActions = new FieldActions();
        FieldAction mapFieldAction = new MapAction();
        ((MapAction) mapFieldAction).setIndex(0);
        fieldActions.getFieldAction().add(mapFieldAction);

        separateOutMappedField.setFieldActions(fieldActions);

        fieldMappings.getFieldMapping().add(separateField);

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertTrue(mappingErrors.hasErrors());
        assertTrue(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());

        mappingUtil.marshallMapping(mapping,"src/test/resources/mappings/MisMatchedModuleTypes.xml");
    }

    @Test
    public void validateAtlasMappingFile_InvalidModuleType() throws Exception {
        AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

        mapping.setName("thisis_a_valid.name");
        mapping.setSourceUri("atlas:xml::2");
        mapping.setTargetUri("atlas:xml::3");

        FieldMappings fieldMappings = new FieldMappings();
        FieldMapping mapFieldMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
        createMockMappedFields(mapping, fieldMappings, mapFieldMapping);

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_InvalidTypeInSeparateField() throws Exception {
        AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

        mapping.setName("thisis a_valid.name");
        mapping.setSourceUri("atlas:java::2");
        mapping.setTargetUri("atlas:java::3");

        FieldMappings fieldMappings = new FieldMappings();
        FieldMapping separateFieldMapping = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);

        fieldMappings.getFieldMapping().add(separateFieldMapping);
        mapping.setFieldMappings(fieldMappings);

        MappedField separateInputMappedField = AtlasModelFactory.createMappedField();
        ((SeparateFieldMapping)separateFieldMapping).setInputField(separateInputMappedField);
        MappedField separateOutMappedField = AtlasModelFactory.createMappedField();

        JavaField bIJavaField = javaModelFactory.createJavaField();
        bIJavaField.setType(FieldType.BOOLEAN);
        bIJavaField.setClassName("java.lang.Boolean");
        bIJavaField.setValue(Boolean.TRUE);
        bIJavaField.setName("inputName");

        separateInputMappedField.setField(bIJavaField);

        JavaField sOJavaField = javaModelFactory.createJavaField();
        sOJavaField.setType(FieldType.STRING);
        sOJavaField.setClassName("java.lang.String");
        sOJavaField.setName("outputName");
        separateOutMappedField.setField(sOJavaField);

        ((SeparateFieldMapping) separateFieldMapping).getOutputFields().getMappedField().add(separateOutMappedField);

        FieldActions fieldActions = new FieldActions();
        FieldAction mapFieldAction = new MapAction();
        ((MapAction) mapFieldAction).setIndex(0);
        fieldActions.getFieldAction().add(mapFieldAction);

        separateOutMappedField.setFieldActions(fieldActions);

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());

        assertThat(mappingErrors.getErrorCount(), is(1));

        AtlasMappingError error = mappingErrors.getAllErrors().get(0);
        assertNotNull(error);
        assertThat("Input.Field", is(error.getField()));
        assertThat("BOOLEAN", is(error.getRejectedValue().toString()));
        assertThat("Input field must be of type STRING for a Separate Mapping.", is(error.getDefaultMessage()));
        assertThat(AtlasMappingError.Level.ERROR, is(error.getLevel()));
    }

    @Test
    public void validateAtlasMappingFile_MisMatchedSourceToTarget() throws Exception {
        AtlasMapping mapping = mappingUtil.loadMapping("src/test/resources/mappings/HappyPathMapping.xml");
        assertNotNull(mapping);

        MapFieldMapping fieldMapping = (MapFieldMapping) mapping.getFieldMappings().getFieldMapping().get(0);

        JavaField in = (JavaField) fieldMapping.getInputField().getField();
         in.setType(FieldType.CHAR);

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertFalse(mappingErrors.hasErrors());
        assertTrue(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_NoClassOnClassPath() throws Exception {
        AtlasMapping mapping = mappingUtil.loadMapping("src/test/resources/mappings/HappyPathMapping.xml");
        assertNotNull(mapping);

        MapFieldMapping fieldMapping = (MapFieldMapping) mapping.getFieldMappings().getFieldMapping().get(0);

        JavaField in = (JavaField) fieldMapping.getInputField().getField();
        in.setClassName("java.lang.String3");

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }


    @Test
    public void validateAtlasMappingFile_WrongModule() throws Exception {
        AtlasMapping mapping = mappingUtil.loadMapping("src/test/resources/mappings/HappyPathMapping.xml");
        assertNotNull(mapping);

        mapping.setSourceUri("atlas:xml:qwerty");

        mapping.setTargetUri("atlas:json:qwerty");

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_PathAndNameNull() throws Exception {
        AtlasMapping mapping = mappingUtil.loadMapping("src/test/resources/mappings/HappyPathMapping.xml");
        assertNotNull(mapping);

        MapFieldMapping fieldMapping = (MapFieldMapping) mapping.getFieldMappings().getFieldMapping().get(0);

        JavaField in = (JavaField) fieldMapping.getInputField().getField();
        in.setName(null);

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());


    }

    @Test
    public void validateAtlasMappingFile_PathNotNull() throws Exception {
        AtlasMapping mapping = mappingUtil.loadMapping("src/test/resources/mappings/HappyPathMapping.xml");
        assertNotNull(mapping);

        MapFieldMapping fieldMapping = (MapFieldMapping) mapping.getFieldMappings().getFieldMapping().get(0);

        JavaField in = (JavaField) fieldMapping.getInputField().getField();
        in.setName(null);
        in.setPath("path");

        JavaModuleMappingValidator validator = new JavaModuleMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());


    }
}

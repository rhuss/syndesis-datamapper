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
package com.mediadriver.atlas.validators.v2;

import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.*;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by slepage on 3/14/17.
 */
public class AtlasMappingValidationTest {

    com.mediadriver.atlas.java.v2.ObjectFactory javaModelFactory = new com.mediadriver.atlas.java.v2.ObjectFactory();

    @Test
    public void validateAtlasMappingFile_HappyPath() throws Exception {

        AtlasMapping mapping = getMappingFullValid();
        assertNotNull(mapping);

        //validation
        AtlasMappingValidator validator = new AtlasMappingValidator(mapping);

        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_HappyPath2() throws Exception {

        AtlasMapping mapping = getMappingFullValid();
        assertNotNull(mapping);

        //validation
        Errors mappingErrors = new AtlasMappingErrors();
        AtlasMappingValidator validator = new AtlasMappingValidator(mapping, mappingErrors);

        validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_InvalidName() throws Exception {

        AtlasMapping mapping = new AtlasMapping();

        mapping.setName("thisis in_valid.name");

        AtlasMappingValidator validator = new AtlasMappingValidator(mapping);

        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertTrue(mappingErrors.hasErrors());
        assertTrue(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());

    }

    @Test
    public void testAtlasMappingUtil() throws Exception {
        AtlasMapping mapping = getMappingFullValid();
        assertNotNull(mapping);
        AtlasMappingUtil atlasMappingUtil = new AtlasMappingUtil();
        final String fileName = "src/test/resources/mappings/HappyPathMapping.xml";
        atlasMappingUtil.marshallMapping(mapping, fileName);
        assertTrue(Files.exists(Paths.get(fileName)));
        AtlasMapping atlasMapping = atlasMappingUtil.loadMapping(fileName);
        assertNotNull(atlasMapping);
    }

    private AtlasMapping getMappingFullValid() throws Exception {
        AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

        mapping.setName("thisis_a_valid.name");

        mapping.setSourceUri("atlas:java::2");

        mapping.setTargetUri("atlas:java::3");

        FieldMappings fieldMappings = new FieldMappings();

        FieldMapping mapFieldMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
        FieldMapping separateField = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);

        // MappedField
        MappedField inputMappedField = AtlasModelFactory.createMappedField();

        JavaField inputJavaField = javaModelFactory.createJavaField();
        inputJavaField.setType(FieldType.STRING);
        inputJavaField.setClassName("com.qwerty.MyClass");
        inputMappedField.setField(inputJavaField);

        MappedField outMappedField = AtlasModelFactory.createMappedField();
        JavaField outputJavaField = javaModelFactory.createJavaField();
        outputJavaField.setType(FieldType.BOOLEAN);
        outMappedField.setField(outputJavaField);

        ((MapFieldMapping) mapFieldMapping).setInputField(inputMappedField);

        ((MapFieldMapping) mapFieldMapping).setOutputField(outMappedField);

        // SeparateField
        MappedField separateInputMappedField = AtlasModelFactory.createMappedField();
        MappedField separateOutMappedField = AtlasModelFactory.createMappedField();

        ((SeparateFieldMapping) separateField).setInputField(separateInputMappedField);
        JavaField sIJavaField = javaModelFactory.createJavaField();
        sIJavaField.setType(FieldType.BOOLEAN);
        sIJavaField.setClassName("org.example.OtherValueObject");
        separateInputMappedField.setField(sIJavaField);

        JavaField sOJavaField = javaModelFactory.createJavaField();
        sOJavaField.setType(FieldType.STRING);
        sOJavaField.setClassName("com.example.ValueObject");
        separateOutMappedField.setField(sOJavaField);

        ((SeparateFieldMapping) separateField).getOutputFields().getMappedField().add(separateOutMappedField);

        FieldActions fieldActions = new FieldActions();
        FieldAction mapFieldAction = new MapAction();
        ((MapAction) mapFieldAction).setIndex(0);
        fieldActions.getFieldAction().add(mapFieldAction);

        separateOutMappedField.setFieldActions(fieldActions);

        fieldMappings.getFieldMapping().add(mapFieldMapping);
        fieldMappings.getFieldMapping().add(separateField);
        mapping.setFieldMappings(fieldMappings);
        return mapping;
    }

    private void debugErrors(Errors mappingErrors) {
        for (AtlasMappingError atlasMappingError : mappingErrors.getAllErrors()) {
            System.out.println(atlasMappingError);
        }
    }

}
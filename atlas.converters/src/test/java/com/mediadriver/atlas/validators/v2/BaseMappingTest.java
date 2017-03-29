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
import com.mediadriver.atlas.validators.v2.core.AtlasMappingError;
import com.mediadriver.atlas.validators.v2.util.AtlasMappingUtil;

/**
 */
public abstract class BaseMappingTest {

    protected com.mediadriver.atlas.java.v2.ObjectFactory javaModelFactory = new com.mediadriver.atlas.java.v2.ObjectFactory();
    protected AtlasMappingUtil mappingUtil = new AtlasMappingUtil();

    protected AtlasMapping getMappingFullValid() throws Exception {
        AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

        mapping.setName("thisis_a_valid.name");

        mapping.setSourceUri("atlas:java?2");

        mapping.setTargetUri("atlas:java?3");

        FieldMappings fieldMappings = new FieldMappings();

        FieldMapping mapFieldMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
        FieldMapping separateField = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);

        // MappedField
        MappedField inputMappedField = AtlasModelFactory.createMappedField();

        JavaField inputJavaField = javaModelFactory.createJavaField();
        inputJavaField.setType(FieldType.STRING);
        inputJavaField.setClassName("java.lang.String");
        inputJavaField.setName("inputName");
        inputMappedField.setField(inputJavaField);

        MappedField outMappedField = AtlasModelFactory.createMappedField();
        JavaField outputJavaField = javaModelFactory.createJavaField();
        outputJavaField.setType(FieldType.STRING);
        outputJavaField.setClassName("java.lang.String");
        outputJavaField.setName("outputName");
        outMappedField.setField(outputJavaField);

        ((MapFieldMapping) mapFieldMapping).setInputField(inputMappedField);

        ((MapFieldMapping) mapFieldMapping).setOutputField(outMappedField);

        // SeparateField
        MappedField separateInputMappedField = AtlasModelFactory.createMappedField();
        MappedField separateOutMappedField = AtlasModelFactory.createMappedField();

        ((SeparateFieldMapping) separateField).setInputField(separateInputMappedField);
        JavaField sIJavaField = javaModelFactory.createJavaField();
        sIJavaField.setType(FieldType.STRING);
        sIJavaField.setClassName("java.lang.String");
        sIJavaField.setName("inputName");
        separateInputMappedField.setField(sIJavaField);

        JavaField sOJavaField = javaModelFactory.createJavaField();
        sOJavaField.setType(FieldType.STRING);
        sOJavaField.setClassName("java.lang.String");
        sOJavaField.setName("outputName");
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

    protected void createMockMappedFields(AtlasMapping mapping, FieldMappings fieldMappings, FieldMapping mapFieldMapping) {
        // Mock MappedField
        MappedField inputMappedField = AtlasModelFactory.createMappedField();
        MockField mockField = new MockField();
        mockField.setName("input.name");
        MockField outMockField = new MockField();
        outMockField.setName("out.name");
        inputMappedField.setField(mockField);
        fieldMappings.getFieldMapping().add(mapFieldMapping);
        ((MapFieldMapping) mapFieldMapping).setInputField(inputMappedField);
        MappedField outMappedField = AtlasModelFactory.createMappedField();
        outMappedField.setField(outMockField);
        ((MapFieldMapping) mapFieldMapping).setOutputField(outMappedField);
        mapping.setFieldMappings(fieldMappings);
    }

    protected void debugErrors(Errors mappingErrors) {
        for (AtlasMappingError atlasMappingError : mappingErrors.getAllErrors()) {
            System.out.println(atlasMappingError);
        }
    }
}

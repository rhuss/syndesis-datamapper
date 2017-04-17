/**
 * Copyright (C) 2017 Red Hat, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mediadriver.atlas.validators.v2.core;

import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.AtlasModelFactory;
import com.mediadriver.atlas.v2.LookupEntryList;
import com.mediadriver.atlas.v2.LookupFieldMapping;
import com.mediadriver.atlas.v2.LookupTable;
import com.mediadriver.atlas.v2.LookupTables;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.validators.v2.BaseMappingTest;
import com.mediadriver.atlas.validators.v2.Errors;
import com.mediadriver.atlas.validators.v2.util.AtlasMappingUtil;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 */
public class AtlasCoreMappingValidationTest extends BaseMappingTest {

    @Test
    public void validateAtlasMappingFile_HappyPath() throws Exception {
        AtlasMapping mapping = getAtlasMappingFullValid();
        assertNotNull(mapping);

        //validation
        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_HappyPath2() throws Exception {
        AtlasMapping mapping = getAtlasMappingFullValid();
        assertNotNull(mapping);

        //validation
        Errors mappingErrors = new AtlasMappingErrors();
        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping, mappingErrors);
        validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_InvalidName() throws Exception {
        AtlasMapping mapping = new AtlasMapping();
        mapping.setName("thisis in_valid.name");

        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();
        assertTrue(mappingErrors.hasErrors());
        assertTrue(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());

    }


    @Test
    public void validateAtlasMappingFile_LookupTablesDuplicateNames() throws Exception {
        AtlasMapping mapping = getAtlasMappingWithLookupTables("duplicate_name", "duplicate_name");
        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());

    }


    @Test
    public void validateAtlasMappingFile_LookupFieldMappingRefNonExistentNames() throws Exception {
        AtlasMapping mapping = getAtlasMappingWithLookupTables("table1", "table2");

        //add one that does not exists
        LookupFieldMapping lookupFieldMapping = AtlasModelFactory.createFieldMapping(LookupFieldMapping.class);
        lookupFieldMapping.setLookupTableName("table3");

        MappedField inputMappedField = AtlasModelFactory.createMappedField();
        createInputJavaField(inputMappedField, "inputName");

        MappedField outMappedField = AtlasModelFactory.createMappedField();
        createInputJavaField(outMappedField, "outputName");

        lookupFieldMapping.setInputField(inputMappedField);
        lookupFieldMapping.setOutputField(outMappedField);

        mapping.getFieldMappings().getFieldMapping().add(lookupFieldMapping);

        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertTrue(mappingErrors.hasErrors());
        assertFalse(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_LookupFieldMappingUnusedLookupTable() throws Exception {
        AtlasMapping mapping = getAtlasMappingFullValid();
        LookupTables lookupTables = new LookupTables();
        mapping.setLookupTables(lookupTables);

        LookupTable lookupTable = AtlasModelFactory.createLookupTable();
        lookupTable.setName("table1");
        lookupTable.setDescription("desc_table1");
        lookupTable.setLookupEntryList(new LookupEntryList());

        LookupTable lookupTable2 = AtlasModelFactory.createLookupTable();
        lookupTable2.setName("table2");
        lookupTable2.setDescription("desc_table2");
        lookupTable2.setLookupEntryList(new LookupEntryList());

        lookupTables.getLookupTable().add(lookupTable);
        lookupTables.getLookupTable().add(lookupTable2);

        LookupFieldMapping lookupFieldMapping = AtlasModelFactory.createFieldMapping(LookupFieldMapping.class);
        lookupFieldMapping.setLookupTableName("table1");

        MappedField inputMappedField = AtlasModelFactory.createMappedField();
        createInputJavaField(inputMappedField, "inputName");

        MappedField outMappedField = AtlasModelFactory.createMappedField();
        createInputJavaField(outMappedField, "outputName");

        lookupFieldMapping.setInputField(inputMappedField);
        lookupFieldMapping.setOutputField(outMappedField);

        mapping.getFieldMappings().getFieldMapping().add(lookupFieldMapping);

        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertTrue(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void validateAtlasMappingFile_NoLookupFieldMappingsWithTablesDefined() throws Exception {
        AtlasMapping mapping = getAtlasMappingFullValid();
        LookupTables lookupTables = new LookupTables();
        mapping.setLookupTables(lookupTables);

        LookupTable lookupTable = AtlasModelFactory.createLookupTable();
        lookupTable.setName("table1");
        lookupTable.setDescription("desc_table1");
        lookupTable.setLookupEntryList(new LookupEntryList());
        lookupTables.getLookupTable().add(lookupTable);


        AtlasCoreMappingValidator validator = new AtlasCoreMappingValidator(mapping);
        Errors mappingErrors = validator.validateAtlasMappingFile();

        assertFalse(mappingErrors.hasErrors());
        assertTrue(mappingErrors.hasWarnings());
        assertFalse(mappingErrors.hasInfos());
    }

    @Test
    public void testAtlasMappingUtil() throws Exception {
        Files.createDirectories(Paths.get("target/mappings"));
        AtlasMapping mapping = getAtlasMappingFullValid();
        assertNotNull(mapping);
        AtlasMappingUtil atlasMappingUtil = new AtlasMappingUtil();
        final String fileName = "target/mappings/HappyPathMapping.xml";
        atlasMappingUtil.marshallMapping(mapping, fileName);
        assertTrue(Files.exists(Paths.get(fileName)));
        AtlasMapping atlasMapping = atlasMappingUtil.loadMapping(fileName);
        assertNotNull(atlasMapping);
    }

}
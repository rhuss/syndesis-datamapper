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
package com.mediadriver.atlas.validators.v2.core;

import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.validators.v2.BaseMappingTest;
import com.mediadriver.atlas.validators.v2.Errors;
import com.mediadriver.atlas.validators.v2.util.AtlasMappingUtil;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 */
public class AtlasCoreMappingValidationTest extends BaseMappingTest{

    @Test
    public void validateAtlasMappingFile_HappyPath() throws Exception {
        AtlasMapping mapping = getMappingFullValid();
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
        AtlasMapping mapping = getMappingFullValid();
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
    public void testAtlasMappingUtil() throws Exception {
        Files.createDirectories(Paths.get("target/mappings"));
        AtlasMapping mapping = getMappingFullValid();
        assertNotNull(mapping);
        AtlasMappingUtil atlasMappingUtil = new AtlasMappingUtil();
        final String fileName = "target/mappings/HappyPathMapping.xml";
        atlasMappingUtil.marshallMapping(mapping, fileName);
        assertTrue(Files.exists(Paths.get(fileName)));
        AtlasMapping atlasMapping = atlasMappingUtil.loadMapping(fileName);
        assertNotNull(atlasMapping);
    }

}
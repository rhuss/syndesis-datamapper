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

import com.mediadriver.atlas.v2.AtlasMapping;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by slepage on 3/15/17.
 */
public class AtlasMappingUtil {

    static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance("com.mediadriver.atlas.v2:com.mediadriver.atlas.java.v2");
        } catch (JAXBException e) {
            System.err.print(e);
        }
    }

    public AtlasMapping loadMapping(String fileName) throws Exception {
        AtlasMapping mapping = null;
        if (jaxbContext != null) {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Path newFilePath = Paths.get(fileName);
            mapping = (AtlasMapping) ((javax.xml.bind.JAXBElement) unmarshaller.unmarshal(newFilePath.toFile())).getValue();
        }
        return mapping;
    }


    public void marshallMapping(AtlasMapping mapping, String fileName) throws Exception {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Path newFilePath = Paths.get(fileName);
        Files.deleteIfExists(newFilePath);
        Path file = Files.createFile(newFilePath);
        marshaller.marshal(mapping, file.toFile());
    }
}

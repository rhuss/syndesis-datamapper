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

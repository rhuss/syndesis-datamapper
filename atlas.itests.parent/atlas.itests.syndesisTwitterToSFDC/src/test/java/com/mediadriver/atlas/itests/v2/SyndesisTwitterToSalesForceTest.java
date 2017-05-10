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
package com.mediadriver.atlas.itests.v2;

import com.mediadriver.atlas.api.v2.AtlasContext;
import com.mediadriver.atlas.api.v2.AtlasContextFactory;
import com.mediadriver.atlas.api.v2.AtlasSession;
import com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory;
import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.service.v2.AtlasService;
import com.mediadriver.atlas.service.v2.TestUriInfo;
import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.AtlasModelFactory;
import com.mediadriver.atlas.v2.FieldType;
import com.mediadriver.atlas.v2.MapAction;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.SeparateFieldMapping;
import io.syndesis.connector.salesforce.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SyndesisTwitterToSalesForceTest {

	private static final Logger logger = LoggerFactory.getLogger(SyndesisTwitterToSalesForceTest.class);

	private AtlasService atlasService = null;
	private AtlasContextFactory atlasContextFactory = null;

	@Before
	public void setUp() {
		atlasService = new AtlasService();
		atlasContextFactory = DefaultAtlasContextFactory.getInstance();
	}

	@After
	public void tearDown() {
		atlasService = null;
		atlasContextFactory = null;
	}

	@Test
	public void testGenerateAndSaveMapping() throws Exception {
		Files.createDirectories(Paths.get("target/mappings"));
		atlasService.createMapping(generateMapping(), generateTestUriInfo("/", "mapping"));
	}

	protected AtlasMapping generateMapping() throws Exception {
		AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

		mapping.setName("twitterStatusToSalesforceContact");
		mapping.setSourceUri("atlas:java?className=twitter4j.Status");
		mapping.setTargetUri("atlas:java?className=io.syndesis.connector.salesforce.Contact");

		SeparateFieldMapping sepMapping = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);
		MappedField nameField = AtlasModelFactory.createMappedField();
		JavaField jNameField = new JavaField();
		jNameField.setName("Name");
		jNameField.setPath("User.name");
		jNameField.setGetMethod("getName");
		jNameField.setType(FieldType.STRING);
		nameField.setField(jNameField);

		MappedField fnField = AtlasModelFactory.createMappedField();
		JavaField jFirstNameField = new JavaField();
		jFirstNameField.setName("FirstName");
		jFirstNameField.setPath("FirstName");
		jFirstNameField.setSetMethod("setFirstName");
		jFirstNameField.setType(FieldType.STRING);
		fnField.setField(jFirstNameField);
		MapAction fnAction = new MapAction();
		fnAction.setIndex(0);
		fnField.getFieldActions().getFieldAction().add(fnAction);

		MappedField lnField = AtlasModelFactory.createMappedField();
		JavaField jLastNameField = new JavaField();
		jLastNameField.setName("LastName");
		jLastNameField.setPath("LastName");
		jLastNameField.setSetMethod("setLastName");
		jLastNameField.setType(FieldType.STRING);
		lnField.setField(jLastNameField);
		MapAction lnAction = new MapAction();
		lnAction.setIndex(1);
		lnField.getFieldActions().getFieldAction().add(lnAction);

		sepMapping.setInputField(nameField);
		sepMapping.getOutputFields().getMappedField().add(fnField);
		sepMapping.getOutputFields().getMappedField().add(lnField);
		mapping.getFieldMappings().getFieldMapping().add(sepMapping);

		MapFieldMapping textDescMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
		MappedField textField = AtlasModelFactory.createMappedField();
		JavaField jTextField = new JavaField();
		jTextField.setName("Text");
		jTextField.setPath("Text");
		jTextField.setGetMethod("getText");
		jTextField.setType(FieldType.STRING);
		textField.setField(jTextField);

		MappedField descField = AtlasModelFactory.createMappedField();
		JavaField jDescField = new JavaField();
		jDescField.setName("Description");
		jDescField.setPath("description");
		jDescField.setSetMethod("setDescription");
		jDescField.setType(FieldType.STRING);
		descField.setField(jDescField);

		textDescMapping.setInputField(textField);
		textDescMapping.setOutputField(descField);
		mapping.getFieldMappings().getFieldMapping().add(textDescMapping);

		MapFieldMapping screenTitleMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
		MappedField screenField = AtlasModelFactory.createMappedField();
		JavaField jScreenField = new JavaField();
		jScreenField.setName("ScreenName");
		jScreenField.setPath("User.screenName");
		jScreenField.setGetMethod("getScreenName");
		jScreenField.setType(FieldType.STRING);
		screenField.setField(jScreenField);

		MappedField titleField = AtlasModelFactory.createMappedField();
		JavaField jTitleField = new JavaField();
		jTitleField.setName("Title");
		jTitleField.setPath("Title");
		jTitleField.setSetMethod("setTitle");
		jTitleField.setType(FieldType.STRING);
		titleField.setField(jTitleField);

		screenTitleMapping.setInputField(screenField);
		screenTitleMapping.setOutputField(titleField);
		mapping.getFieldMappings().getFieldMapping().add(screenTitleMapping);

		return mapping;
	}


	@Test
	public void testProcessLoadedMapping() throws Exception {
		String filename = "src/test/resources/atlasmapping-UI.820809.xml";
		AtlasMapping mapping = atlasService.getMappingFromFile(filename);
		verifyMappingProcessing(mapping);
	}

	@Test
	public void testProcessGeneratedMapping() throws Exception {
		verifyMappingProcessing(generateMapping());
	}

	public void verifyMappingProcessing(AtlasMapping mapping) throws Exception {
		AtlasContext context = atlasContextFactory.createContext(mapping);
		AtlasSession session = context.createSession();
		Status twitterStatus = generateTwitterStatus();
		session.setInput(twitterStatus);

		System.out.println("Twitter.Status");
		System.out.println("\tUser.name: " + twitterStatus.getUser().getName());
		System.out.println("\tUser.screenName: " + twitterStatus.getUser().getScreenName());
		System.out.println("\tText: " + twitterStatus.getText());

		context.process(session);

		assertNotNull(session);
		Object target = session.getOutput();
		assertNotNull(target);
		assertTrue("io.syndesis.connector.salesforce.Contact".equals(target.getClass().getCanonicalName()));

		System.out.println("SFDC.Contact");
		System.out.println("\tFirstName: " + ((Contact)target).getFirstName());
		System.out.println("\tLastName: " + ((Contact)target).getLastName());
		System.out.println("\tTitle: " + ((Contact)target).getTitle());
		System.out.println("\tDescription: " + ((Contact)target).getDescription());
	}

	protected UriInfo generateTestUriInfo(String baseUri, String absoluteUri) throws Exception {
		return new TestUriInfo(new URI(baseUri), new URI(absoluteUri));
	}

	protected Status generateTwitterStatus() {
		MockStatus status = new MockStatus();
		MockUser user = new MockUser();
		user.setName("Bob Vila");
		user.setScreenName("bobvila1982");
		status.setUser(user);
		status.setText("Let's build a house!");
		return status;
	}

}

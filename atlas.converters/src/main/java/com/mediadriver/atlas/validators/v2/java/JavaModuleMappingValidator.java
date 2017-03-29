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
package com.mediadriver.atlas.validators.v2.java;

import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.FieldMapping;
import com.mediadriver.atlas.v2.FieldType;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.MappedFields;
import com.mediadriver.atlas.v2.SeparateFieldMapping;
import com.mediadriver.atlas.validators.v2.Errors;
import com.mediadriver.atlas.validators.v2.NonNullValidator;
import com.mediadriver.atlas.validators.v2.Validator;
import com.mediadriver.atlas.validators.v2.core.AtlasMappingError;
import com.mediadriver.atlas.validators.v2.core.AtlasMappingErrors;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class JavaModuleMappingValidator {

    private AtlasMapping mapping;
    private Errors errors;
    private static Map<String, Validator> validatorMap = new HashMap<>();
    private static Map<String, Integer> versionMap = new HashMap<>();

    static {
        NonNullValidator javaFileNameNonNullValidator = new NonNullValidator("JavaField.Name", "The name element must not be null nor empty");
        NonNullValidator javaFilePathNonNullValidator = new NonNullValidator("JavaField.Path", "The path element must not be null nor empty");
        NonNullValidator inputFieldTypeNonNullValidator = new NonNullValidator("Input.Field.Type", "Field type should not be null nor empty");
        NonNullValidator outputFieldTypeNonNullValidator = new NonNullValidator("Output.Field.Type", "Field type should not be null nor empty");
        NonNullValidator fieldTypeNonNullValidator = new NonNullValidator("Field.Type", "Filed type should not be null nor empty");

        validatorMap.put("java.field.type.not.null", fieldTypeNonNullValidator);
        validatorMap.put("java.field.name.not.null", javaFileNameNonNullValidator);
        validatorMap.put("java.field.path.not.null", javaFilePathNonNullValidator);
        validatorMap.put("input.field.type.not.null", inputFieldTypeNonNullValidator);
        validatorMap.put("output.field.type.not.null", outputFieldTypeNonNullValidator);

        versionMap.put("1.8", 52);
        versionMap.put("1.7", 51);
        versionMap.put("1.6", 50);
        versionMap.put("1.5", 49);
        versionMap.put("1.4", 48);
        versionMap.put("1.3", 47);
        versionMap.put("1.2", 46);
        versionMap.put("1.1", 45);


    }

    public JavaModuleMappingValidator(AtlasMapping mapping, Errors errors) {
        this.mapping = mapping;
        this.errors = errors;
    }

    public JavaModuleMappingValidator(AtlasMapping mapping) {
        this.mapping = mapping;
        this.errors = new AtlasMappingErrors();
    }

    public Errors validateAtlasMappingFile() {
        validateFieldMappings();
        return errors;
    }

    private void validateFieldMappings() {
        if (mapping.getFieldMappings() != null) {
            if (mapping.getFieldMappings().getFieldMapping() != null && !mapping.getFieldMappings().getFieldMapping().isEmpty()) {
                for (FieldMapping fieldMapping : mapping.getFieldMappings().getFieldMapping()) {
                    if (fieldMapping.getClass().isAssignableFrom(MapFieldMapping.class)) {
                        evaluateMapFieldMapping((MapFieldMapping) fieldMapping);
                    } else if (fieldMapping.getClass().isAssignableFrom(SeparateFieldMapping.class)) {
                        evaluateSeparateFieldMapping((SeparateFieldMapping) fieldMapping);
                    }
                }
            }
        }
    }

    private void evaluateMapFieldMapping(MapFieldMapping fieldMapping) {
        JavaField inputField = null;
        JavaField outField = null;
        MappedField input = fieldMapping.getInputField();
        MappedField output = fieldMapping.getOutputField();

        if (input != null && input.getField().getClass().isAssignableFrom(JavaField.class)) {
            inputField = (JavaField) input.getField();
        } else {
            errors.getAllErrors().add(new AtlasMappingError("Input.Field", null, "Input field is not supported by Java Module validator. ", AtlasMappingError.Level.ERROR));
        }
        if (output != null && output.getField().getClass().isAssignableFrom(JavaField.class)) {
            outField = (JavaField) output.getField();
        } else {
            errors.getAllErrors().add(new AtlasMappingError("Output.Field", null, "Output field is not supported by Java Module validator. ", AtlasMappingError.Level.ERROR));
        }

        if (inputField != null && outField != null) {
            validateJavaField(inputField, "input");
            validateJavaField(outField, "output");
            validateSourceAndTargetTypes(inputField, outField);
        }
    }

    private void validateSourceAndTargetTypes(JavaField inputField, JavaField outField) {
        if (inputField.getType().compareTo(outField.getType()) != 0) {
            errors.getAllErrors().add(new AtlasMappingError("Field.Input/Output", inputField.getType().value() + " --> " + outField.getType().value(), "Output field type does not match input filed type, may require a converter.", AtlasMappingError.Level.WARN));
        }
    }

    private void evaluateSeparateFieldMapping(SeparateFieldMapping fieldMapping) {
        JavaField inputField = null;
        JavaField outField = null;
        // input
        MappedField input = fieldMapping.getInputField();
        if (input.getField().getClass().isAssignableFrom(JavaField.class)) {
            inputField = (JavaField) input.getField();
        } else {
            errors.getAllErrors().add(new AtlasMappingError("Inout.Field", null, "Input field is not supported by Java Module validator. ", AtlasMappingError.Level.ERROR));
        }
        // check that the input field is of type String else error
        if (inputField != null && inputField.getType().compareTo(FieldType.STRING) != 0) {
            errors.getAllErrors().add(new AtlasMappingError("Input.Field", inputField.getType().toString(), "Input field must be of type " + FieldType.STRING + " for a Separate Mapping.", AtlasMappingError.Level.ERROR));
        }

        validateJavaField(inputField, "input");
        //TODO call JavaModule.isSupported() on field  (false = ERROR)
        // output
        MappedFields outputFields = fieldMapping.getOutputFields();
        for (MappedField mappedField : outputFields.getMappedField()) {
            if (mappedField.getField().getClass().isAssignableFrom(JavaField.class)) {
                outField = (JavaField) mappedField.getField();
                validateJavaField(outField, "output");
                //TODO call JavaModule.isSupported() on field  (false = ERROR)
            } else {
                errors.getAllErrors().add(new AtlasMappingError("Output.Field", outField, "Output field is not supported by Java Module validator. ", AtlasMappingError.Level.ERROR));
            }
        }
    }

    private void validateJavaField(JavaField field, String direction) {
        //TODO check that it is a valid type on the AtlasContext

        validatorMap.get("java.field.type.not.null").validate(field, errors, AtlasMappingError.Level.WARN);
        if ("input".equalsIgnoreCase(direction)) {
            // assert that source uri contains java module
            if (!mapping.getSourceUri().contains("java")) {
                errors.getAllErrors().add(new AtlasMappingError(String.format("Field.%s.Name/Path", direction), field, "Source module does not support source field.", AtlasMappingError.Level.ERROR));
            }
            if (field != null) {
                validatorMap.get("input.field.type.not.null").validate(field.getType(), errors, AtlasMappingError.Level.WARN);
            }
        } else {
            // assert that target uri contains java module
            if (!mapping.getTargetUri().contains("java")) {
                errors.getAllErrors().add(new AtlasMappingError(String.format("Field.%s.Name/Path", direction), field, "Target module does not support target field. ", AtlasMappingError.Level.ERROR));
            }
            if (field != null) {
                validatorMap.get("output.field.type.not.null").validate(field.getType(), errors, AtlasMappingError.Level.WARN);
            }
        }
        if (field != null) {
            if ((field.getName() == null && field.getPath() == null)) {
                errors.getAllErrors().add(new AtlasMappingError(String.format("Field.%s.Name/Path", direction), null, "Both path and name are null. One or the other must be defined.", AtlasMappingError.Level.ERROR));
            } else if (field.getName() != null && field.getPath() == null) {
                validatorMap.get("java.field.name.not.null").validate(field.getName(), errors);
            } else if (field.getName() == null && field.getPath() != null) {
                validatorMap.get("java.field.path.not.null").validate(field.getPath(), errors);
            }
            evaluateClass(field);
        }
    }

    private void evaluateClass(JavaField field) {
        String clazzName = field.getClassName();
        if (clazzName != null && !clazzName.isEmpty()) {
            try {
                // exception means not on classpath
                JavaClass c = Repository.lookupClass(clazzName);
                if (c.getMajor() > versionMap.get(System.getProperty("java.vm.specification.version"))) {
                    errors.getAllErrors().add(new AtlasMappingError("Field.Classname", clazzName, String.format("Class for field is compiled against %d but current JDK is %d.", c.getMajor(), versionMap.get(System.getProperty("java.vm.specification.version"))), AtlasMappingError.Level.ERROR));
                }
            } catch (ClassNotFoundException e) {
                errors.getAllErrors().add(new AtlasMappingError("Field.Classname", clazzName, "Class for field is not found on the classpath.", AtlasMappingError.Level.ERROR));
            }
        }
    }
}

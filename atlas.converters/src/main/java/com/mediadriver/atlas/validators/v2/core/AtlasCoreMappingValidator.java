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
import com.mediadriver.atlas.v2.FieldAction;
import com.mediadriver.atlas.v2.FieldMapping;
import com.mediadriver.atlas.v2.LookupFieldMapping;
import com.mediadriver.atlas.v2.LookupTable;
import com.mediadriver.atlas.v2.MapAction;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.SeparateFieldMapping;
import com.mediadriver.atlas.validators.v2.CompositeValidator;
import com.mediadriver.atlas.validators.v2.Errors;
import com.mediadriver.atlas.validators.v2.LookupTableNameValidator;
import com.mediadriver.atlas.validators.v2.NonNullValidator;
import com.mediadriver.atlas.validators.v2.NotEmptyValidator;
import com.mediadriver.atlas.validators.v2.PositiveIntegerValidator;
import com.mediadriver.atlas.validators.v2.StringPatternValidator;
import com.mediadriver.atlas.validators.v2.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 */
public class AtlasCoreMappingValidator {

    private AtlasMapping mapping;
    private Errors errors;

    private static Map<String, Validator> validatorMap = new HashMap<>();

    static {
        NonNullValidator sourceURINotNullOrEmptyValidator = new NonNullValidator("Source.URI", "Source.URI must not be null nor empty");
        NonNullValidator targetURINotNullOrEmptyValidator = new NonNullValidator("Target.URI", "Target.URI must not be null nor empty");
        StringPatternValidator namePatternValidator = new StringPatternValidator("Mapping.Name", "Mapping name must not contain spaces nor special characters other than period (.) and underscore (_)", "[^A-Za-z0-9_.]");
        NonNullValidator nameNotNullOrEmptyValidator = new NonNullValidator("Mapping.Name", "Mapping name must not be null nor empty");
        CompositeValidator nameValidator = new CompositeValidator(nameNotNullOrEmptyValidator, namePatternValidator);
        NonNullValidator fieldNamesNotNullValidator = new NonNullValidator("Field.Mappings", "Field mappings must not be null");
        NotEmptyValidator fieldNamesNotEmptyValidator = new NotEmptyValidator("Field.Mappings", "Field mappings should not be empty");
        NonNullValidator inputNonNullValidator = new NonNullValidator("MapFieldMapping.Input", "Input element must not be null");
        NonNullValidator inputFieldNonNullValidator = new NonNullValidator("MapFieldMapping.Input.Field", "Input field element must not be null");
        NonNullValidator outputNonNullValidator = new NonNullValidator("MapFieldMapping.Output", "Output element should not be null");
        NonNullValidator outputFieldNonNullValidator = new NonNullValidator("MapFieldMapping.Output.Field", "Output field element should not be null");
        NonNullValidator separateInputNonNullValidator = new NonNullValidator("SeparateFieldMapping.Input", "Input element must not be null");
        NonNullValidator separateInputFieldNonNullValidator = new NonNullValidator("SeparateFieldMapping.Input.Field", "Input field element must not be null");

        NonNullValidator separateOutputNonNullValidator = new NonNullValidator("SeparateFieldMapping.Output", "Output element should not be null");
        NotEmptyValidator separateOutputNotEmptyValidator = new NotEmptyValidator("SeparateFieldMapping.Output.Fields", "Output elements should not be empty");
        NonNullValidator separateOutputFieldNonNullValidator = new NonNullValidator("SeparateFieldMapping.Output.FieldActions", "Output field actions cannot not be null");
        NotEmptyValidator separateOutputNotEmptyFieldActionValidator = new NotEmptyValidator("SeparateFieldMapping.Output.Fields.FieldActions", "Field actions cannot be null or empty");
        PositiveIntegerValidator separateOutputMapActionPositiveIntegerValidator = new PositiveIntegerValidator("SeparateFieldMapping.Output.Fields.FieldActions.MapAction.Index", "MapAction index must exists and be greater than or equal to zero (0)");

        LookupTableNameValidator lookupTableNameValidator = new LookupTableNameValidator("lookuptables.lookuptable.name", "LookupTables contain duplicated LookupTable names.");


        validatorMap.put("source.uri", sourceURINotNullOrEmptyValidator);
        validatorMap.put("target.uri", targetURINotNullOrEmptyValidator);
        validatorMap.put("mapping.name", nameValidator);
        validatorMap.put("field.names.not.null", fieldNamesNotNullValidator);
        validatorMap.put("field.names.not.empty", fieldNamesNotEmptyValidator);
        validatorMap.put("input.not.null", inputNonNullValidator);
        validatorMap.put("input.field.not.null", inputFieldNonNullValidator);
        validatorMap.put("output.not.null", outputNonNullValidator);
        validatorMap.put("output.field.not.null", outputFieldNonNullValidator);

        validatorMap.put("separate.input.not.null", separateInputNonNullValidator);
        validatorMap.put("separate.input.field.not.null", separateInputFieldNonNullValidator);
        validatorMap.put("separate.output.not.null", separateOutputNonNullValidator);
        validatorMap.put("separate.output.not.empty", separateOutputNotEmptyValidator);
        validatorMap.put("separate.output.field.not.null", separateOutputFieldNonNullValidator);
        validatorMap.put("separate.output.field.field.action.not.empty", separateOutputNotEmptyFieldActionValidator);
        validatorMap.put("separate.output.field.field.action.index.positive", separateOutputMapActionPositiveIntegerValidator);
        validatorMap.put("lookuptable.name.check.for.duplicate", lookupTableNameValidator);

    }

    public AtlasCoreMappingValidator(AtlasMapping mapping, Errors errors) {
        this.mapping = mapping;
        this.errors = errors;
    }

    public AtlasCoreMappingValidator(AtlasMapping mapping) {
        this.mapping = mapping;
        this.errors = new AtlasMappingErrors();
    }

    public Errors validateAtlasMappingFile() {
        validatorMap.get("mapping.name").validate(mapping.getName(), errors);
        validatorMap.get("source.uri").validate(mapping.getSourceUri(), errors);
        validatorMap.get("target.uri").validate(mapping.getTargetUri(), errors, AtlasMappingError.Level.WARN);
        validateFieldMappings();
        return errors;
    }

    private void validateFieldMappings() {
        validatorMap.get("field.names.not.null").validate(mapping.getFieldMappings(), errors);
        if (mapping.getFieldMappings() != null) {
            validatorMap.get("field.names.not.empty").validate(mapping.getFieldMappings().getFieldMapping(), errors, AtlasMappingError.Level.WARN);

            List<FieldMapping> fieldMappings = mapping.getFieldMappings().getFieldMapping();
            if (fieldMappings != null && !fieldMappings.isEmpty()) {
                List<MapFieldMapping> mapFieldMappings = fieldMappings.stream()
                        .filter(p -> p instanceof MapFieldMapping)
                        .map(p -> (MapFieldMapping) p)
                        .collect(Collectors.toList());
                List<SeparateFieldMapping> separateFieldMappings = fieldMappings.stream()
                        .filter(p -> p instanceof SeparateFieldMapping)
                        .map(p -> (SeparateFieldMapping) p)
                        .collect(Collectors.toList());
                List<LookupFieldMapping> lookupFieldMappings = fieldMappings.stream()
                        .filter(p -> p instanceof LookupFieldMapping)
                        .map(p -> (LookupFieldMapping) p)
                        .collect(Collectors.toList());
                validateMappingFieldMapping(mapFieldMappings);
                validateSeparateFieldMapping(separateFieldMappings);
                validateLookupTables(lookupFieldMappings);
            }
        }
    }

    private void validateLookupTables(List<LookupFieldMapping> lookupFieldMappings) {
        if (mapping.getLookupTables() != null && !mapping.getLookupTables().getLookupTable().isEmpty()) {
            //check for duplicate names
            validatorMap.get("lookuptable.name.check.for.duplicate").validate(mapping.getLookupTables(), errors);
            if (lookupFieldMappings.isEmpty()) {
                // is this an error?
                errors.addError(new AtlasMappingError("lookup.fields.missing", null, "LookupTables are defined but no LookupFields are utilized.", AtlasMappingError.Level.WARN));
            } else {
                validateLookupFieldMapping(lookupFieldMappings, mapping.getLookupTables().getLookupTable());
            }
        }
    }

    //mapping field validations
    private void validateLookupFieldMapping(List<LookupFieldMapping> fieldMappings, List<LookupTable> lookupTables) {
        Set<String> lookupFieldMappingTableNameRefs =
                fieldMappings.stream()
                        .map(LookupFieldMapping::getLookupTableName)
                        .collect(Collectors.toSet());

        Set<String> tableNames = lookupTables.stream()
                .map(LookupTable::getName).collect(Collectors.toSet());

        if (!lookupFieldMappingTableNameRefs.isEmpty() && !tableNames.isEmpty()) {
            Set<String> disjoint = Stream.concat(lookupFieldMappingTableNameRefs.stream(), tableNames.stream())
                    .collect(Collectors.toMap(Function.identity(), t -> true, (a, b) -> null))
                    .keySet();
            if (!disjoint.isEmpty()) {

                boolean isInFieldList = !lookupFieldMappingTableNameRefs.stream().filter(disjoint::contains).collect(Collectors.toList()).isEmpty();
                boolean isInTableNameList = !tableNames.stream().filter(disjoint::contains).collect(Collectors.toList()).isEmpty();
                //which list has the disjoin.... if its the lookup fields then ERROR
                if (isInFieldList) {
                    errors.addError(new AtlasMappingError("lookupfield.tablename", disjoint.toString(), "At least one LookupFieldMapping references a non existent LookupTable name in the mapping.", AtlasMappingError.Level.ERROR));
                }

                //check that if a name exists in table names that at least one field mapping uses it, else WARN
                if (isInTableNameList) {
                    errors.addError(new AtlasMappingError("lookupfield.tablename", disjoint.toString(), "A LookupTable is defined but not used by any LookupField.", AtlasMappingError.Level.WARN));
                }
            }
        }

        for (LookupFieldMapping fieldMapping : fieldMappings) {
            if (fieldMapping.getInputField() != null) {
                validatorMap.get("input.field.not.null").validate(fieldMapping.getInputField().getField(), errors);
            }
            validatorMap.get("output.not.null").validate(fieldMapping.getOutputField(), errors, AtlasMappingError.Level.WARN);
            if (fieldMapping.getOutputField() != null) {
                validatorMap.get("output.field.not.null").validate(fieldMapping.getOutputField().getField(), errors, AtlasMappingError.Level.WARN);
            }
        }

    }

    private void validateMappingFieldMapping(List<MapFieldMapping> fieldMappings) {
        for (MapFieldMapping fieldMapping : fieldMappings) {
            validatorMap.get("input.not.null").validate(fieldMapping.getInputField(), errors);
            if (fieldMapping.getInputField() != null) {
                validatorMap.get("input.field.not.null").validate(fieldMapping.getInputField().getField(), errors);
            }
            validatorMap.get("output.not.null").validate(fieldMapping.getOutputField(), errors, AtlasMappingError.Level.WARN);
            if (fieldMapping.getOutputField() != null) {
                validatorMap.get("output.field.not.null").validate(fieldMapping.getOutputField().getField(), errors, AtlasMappingError.Level.WARN);
            }
        }
    }

    private void validateSeparateFieldMapping(List<SeparateFieldMapping> fieldMappings) {
        for (SeparateFieldMapping fieldMapping : fieldMappings) {
            validatorMap.get("separate.input.not.null").validate(fieldMapping.getInputField(), errors);
            if (fieldMapping.getInputField() != null) {
                validatorMap.get("separate.input.field.not.null").validate(fieldMapping.getInputField().getField(), errors);
                // source must be a String type
            }

            validatorMap.get("separate.output.not.null").validate(fieldMapping.getOutputFields(), errors, AtlasMappingError.Level.WARN);
            validatorMap.get("separate.output.not.empty").validate(fieldMapping.getOutputFields().getMappedField(), errors, AtlasMappingError.Level.WARN);

            if (fieldMapping.getOutputFields() != null) {
                for (MappedField mappedField : fieldMapping.getOutputFields().getMappedField()) {
                    validatorMap.get("separate.output.field.not.null").validate(mappedField.getFieldActions(), errors);
                    if (mappedField.getFieldActions() != null) {
                        validatorMap.get("separate.output.field.field.action.not.empty").validate(mappedField.getFieldActions().getFieldAction(), errors);
                        for (FieldAction fieldAction : mappedField.getFieldActions().getFieldAction()) {
                            validatorMap.get("separate.output.field.field.action.index.positive").validate(((MapAction) fieldAction).getIndex(), errors);
                        }
                    }
                }
            }
        }

    }
}
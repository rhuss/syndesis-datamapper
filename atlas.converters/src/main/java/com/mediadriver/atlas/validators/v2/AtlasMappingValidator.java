package com.mediadriver.atlas.validators.v2;

import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.FieldAction;
import com.mediadriver.atlas.v2.FieldMapping;
import com.mediadriver.atlas.v2.MapAction;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.SeparateFieldMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by slepage on 3/14/17.
 */
public class AtlasMappingValidator {

    private AtlasMapping mapping;
    private Errors errors;

    private static Map<String, Validator> validatorMap = new HashMap<>();

    static {
        NonNullValidator sourceURINotNullOrEmptyValidator = new NonNullValidator("Source.URI", "Source.URI must not be null nor empty");
        NonNullValidator targetURINotNullOrEmptyValidator = new NonNullValidator("Target.URI", "Target.URI must not be null nor empty");
        StringPatternValidator namePatternValidator = new StringPatternValidator("Mapping.Name", "Mapping name must not contain spaces nor special characters other than period (.) and underscore (_)", "[^A-Za-z0-9_.]");
        NonNullValidator nameNotNullOrEmptyValidator = new NonNullValidator("Mapping.Name", "Mapping name must not be null nor empty");
        AtlasMappingCompositeValidator nameValidator = new AtlasMappingCompositeValidator(nameNotNullOrEmptyValidator, namePatternValidator);
        NonNullValidator fieldNamesNotNullValidator = new NonNullValidator("Field.Mappings", "Field mappings must not be null");
        NotEmptyValidator fieldNamesNotEmptyValidator = new NotEmptyValidator("Field.Mappings", "Field mappings should not be empty");
        NonNullValidator inputNonNullValidator = new NonNullValidator("MapFieldMapping.Input", "Input element must not be null");
        NonNullValidator inputFieldNonNullValidator = new NonNullValidator("MapFieldMapping.Input.Field", "Input field element must not be null");
        NonNullValidator outputNonNullValidator = new NonNullValidator("MapFieldMapping.Output", "Output element should not be null");
        NonNullValidator outputFieldNonNullValidator = new NonNullValidator("MapFieldMapping.Output.Field", "Output field element should not be null");
        NonNullValidator inputFieldTypeNonNullValidator = new NonNullValidator("Input.Field.Type", "Field type should not be null nor empty");
        NonNullValidator outputFieldTypeNonNullValidator = new NonNullValidator("Output.Field.Type", "Field type should not be null nor empty");
        NonNullValidator separateInputNonNullValidator = new NonNullValidator("SeparateFieldMapping.Input", "Input element must not be null");
        NonNullValidator separateInputFieldNonNullValidator = new NonNullValidator("SeparateFieldMapping.Input.Field", "Input field element must not be null");
        NonNullValidator fieldTypeNonNullValidator = new NonNullValidator("Field.Type", "Filed type should not be null nor empty");
        NonNullValidator separateOutputNonNullValidator = new NonNullValidator("SeparateFieldMapping.Output", "Output element should not be null");
        NotEmptyValidator separateOutputNotEmptyValidator = new NotEmptyValidator("SeparateFieldMapping.Output.Fields", "Output elements should not be empty");
        NonNullValidator separateOutputFieldNonNullValidator = new NonNullValidator("SeparateFieldMapping.Output.FieldActions", "Output field actions cannot not be null");
        NotEmptyValidator separateOutputNotEmptyFieldActionValidator = new NotEmptyValidator("SeparateFieldMapping.Output.Fields.FieldActions", "Field actions cannot be null or empty");
        PositiveIntegerValidator separateOutputMapActionPositiveIntegerValidator = new PositiveIntegerValidator("SeparateFieldMapping.Output.Fields.FieldActions.MapAction.Index", "MapAction index must exists and be greater than or equal to zero (0)");

        validatorMap.put("source.uri", sourceURINotNullOrEmptyValidator);
        validatorMap.put("target.uri", targetURINotNullOrEmptyValidator);
        validatorMap.put("mapping.name", nameValidator);
        validatorMap.put("field.names.not.null", fieldNamesNotNullValidator);
        validatorMap.put("field.names.not.empty", fieldNamesNotEmptyValidator);
        validatorMap.put("input.not.null", inputNonNullValidator);
        validatorMap.put("input.field.not.null", inputFieldNonNullValidator);
        validatorMap.put("output.not.null", outputNonNullValidator);
        validatorMap.put("output.field.not.null", outputFieldNonNullValidator);
        validatorMap.put("input.field.type.not.null", inputFieldTypeNonNullValidator);
        validatorMap.put("output.field.type.not.null", outputFieldTypeNonNullValidator);
        validatorMap.put("separate.input.not.null", separateInputNonNullValidator);
        validatorMap.put("separate.input.field.not.null", separateInputFieldNonNullValidator);
        validatorMap.put("separate.output.not.null", separateOutputNonNullValidator);
        validatorMap.put("separate.output.not.empty", separateOutputNotEmptyValidator);
        validatorMap.put("separate.output.field.not.null", separateOutputFieldNonNullValidator);
        validatorMap.put("separate.output.field.field.action.not.empty", separateOutputNotEmptyFieldActionValidator);
        validatorMap.put("separate.output.field.field.action.index.positive", separateOutputMapActionPositiveIntegerValidator);
        validatorMap.put("java.field.type.not.null", fieldTypeNonNullValidator);
    }

    public AtlasMappingValidator(AtlasMapping mapping, Errors errors) {
        this.mapping = mapping;
        this.errors = errors;
    }

    public AtlasMappingValidator(AtlasMapping mapping) {
        this.mapping = mapping;
        this.errors = new AtlasMappingErrors();
    }

    public Errors validateAtlasMappingFile() {
        validatorMap.get("mapping.name").validate(mapping.getName(), errors);
        validatorMap.get("source.uri").validate(mapping.getSourceUri(), errors);
        validatorMap.get("target.uri").validate(mapping.getTargetUri(), errors, AtlasMappingError.Level.WARN);
        validateFieldMappings(mapping, errors);
        return errors;
    }

    private void validateFieldMappings(AtlasMapping mapping, Errors errors) {
        validatorMap.get("field.names.not.null").validate(mapping.getFieldMappings(), errors);
        if (mapping.getFieldMappings() != null) {
            validatorMap.get("field.names.not.empty").validate(mapping.getFieldMappings().getFieldMapping(), errors, AtlasMappingError.Level.WARN);
            if (mapping.getFieldMappings().getFieldMapping() != null && !mapping.getFieldMappings().getFieldMapping().isEmpty()) {
                for (FieldMapping fieldMapping : mapping.getFieldMappings().getFieldMapping()) {
                    if (fieldMapping.getClass().isAssignableFrom(MapFieldMapping.class)) {
                        validateMappingFieldMapping((MapFieldMapping) fieldMapping, errors);
                    } else if (fieldMapping.getClass().isAssignableFrom(SeparateFieldMapping.class)) {
                        validateSeparateFieldMapping((SeparateFieldMapping) fieldMapping, errors);
                    }
                }
            }
        }
    }

    private void validateMappingFieldMapping(MapFieldMapping fieldMapping, Errors errors) {
        validatorMap.get("input.not.null").validate(fieldMapping.getInputField(), errors);
        if (fieldMapping.getInputField() != null) {
            validatorMap.get("input.field.not.null").validate(fieldMapping.getInputField().getField(), errors);
            validateJavaFieldType(fieldMapping, errors, "input");
        }
        validatorMap.get("output.not.null").validate(fieldMapping.getOutputField(), errors, AtlasMappingError.Level.WARN);
        if (fieldMapping.getOutputField() != null) {
            validatorMap.get("output.field.not.null").validate(fieldMapping.getOutputField().getField(), errors, AtlasMappingError.Level.WARN);
            if (fieldMapping.getOutputField().getField().getClass().isAssignableFrom(com.mediadriver.atlas.java.v2.JavaField.class)) {
                validateJavaFieldType(fieldMapping, errors, "output");
            }
        }
    }

    private void validateJavaFieldType(MapFieldMapping fieldMapping, Errors errors, String direction) {
        if (fieldMapping.getInputField().getField().getClass().isAssignableFrom(JavaField.class)) {
            if (direction.contains("input")) {
                //TODO check that it is a valid type on the AtlasContext
                JavaField field = (JavaField) fieldMapping.getInputField().getField();
                validatorMap.get("input.field.type.not.null").validate(field.getType(), errors, AtlasMappingError.Level.WARN);
            } else {
                //TODO check that it is a valid type on the AtlasContext
                JavaField field = (JavaField) fieldMapping.getOutputField().getField();
                validatorMap.get("output.field.type.not.null").validate(field.getType(), errors, AtlasMappingError.Level.WARN);
            }
        }
    }

    private void validateSeparateFieldMapping(SeparateFieldMapping fieldMapping, Errors errors) {
        validatorMap.get("separate.input.not.null").validate(fieldMapping.getInputField(), errors);
        if (fieldMapping.getInputField() != null) {
            validatorMap.get("separate.input.field.not.null").validate(fieldMapping.getInputField().getField(), errors);
        }

        if (fieldMapping.getInputField().getField().getClass().isAssignableFrom(com.mediadriver.atlas.java.v2.JavaField.class)) {
            com.mediadriver.atlas.java.v2.JavaField field = (JavaField) fieldMapping.getInputField().getField();
            //TODO check that it is a valid type on the AtlasContext
            validatorMap.get("java.field.type.not.null").validate(field, errors, AtlasMappingError.Level.WARN);
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

"use strict";
var transition_model_1 = require('../models/transition.model');
var mapping_model_1 = require('../models/mapping.model');
var field_model_1 = require('../models/field.model');
var MappingSerializer = (function () {
    function MappingSerializer() {
    }
    MappingSerializer.serializeMappings = function (mappingDefinition, inputDoc, outputDoc) {
        var jsonMappings = [];
        for (var _i = 0, _a = mappingDefinition.mappings; _i < _a.length; _i++) {
            var m = _a[_i];
            var isSeparateMapping = (m.transition.mode == transition_model_1.TransitionMode.SEPARATE);
            var jsonMapping;
            if (isSeparateMapping) {
                var delimiter = (m.transition.delimiter == transition_model_1.TransitionDelimiter.SPACE) ? "SPACE" : "COMMA";
                jsonMapping = {
                    "jsonType": "com.mediadriver.atlas.v2.SeparateFieldMapping",
                    "inputField": MappingSerializer.serializeFields(m.inputFieldPaths, inputDoc, m, false)[0],
                    "outputFields": {
                        "mappedField": MappingSerializer.serializeFields(m.outputFieldPaths, outputDoc, m, true)
                    },
                    "strategy": delimiter
                };
            }
            else {
                jsonMapping = {
                    "jsonType": "com.mediadriver.atlas.v2.MapFieldMapping",
                    "inputField": MappingSerializer.serializeFields(m.inputFieldPaths, inputDoc, m, false)[0],
                    "outputField": MappingSerializer.serializeFields(m.outputFieldPaths, outputDoc, m, false)[0]
                };
            }
            jsonMappings.push(jsonMapping);
        }
        if (mappingDefinition.mappingUUID == null) {
            mappingDefinition.mappingUUID = "UI." + Math.floor((Math.random() * 1000000) + 1).toString();
        }
        var payload = {
            "AtlasMapping": {
                "jsonType": "com.mediadriver.atlas.v2.AtlasMapping",
                "fieldMappings": {
                    "fieldMapping": jsonMappings
                },
                "name": mappingDefinition.mappingUUID
            }
        };
        return payload;
    };
    MappingSerializer.serializeFields = function (fieldPaths, docDef, mapping, includeIndexes) {
        var mappingFieldActions = null;
        var fieldsJson = [];
        for (var _i = 0, fieldPaths_1 = fieldPaths; _i < fieldPaths_1.length; _i++) {
            var fieldPath = fieldPaths_1[_i];
            var field = docDef.getField(fieldPath);
            if (includeIndexes) {
                var separatorIndex = mapping.fieldSeparatorIndexes[field.path];
                mappingFieldActions = {
                    "fieldAction": [
                        {
                            "jsonType": "com.mediadriver.atlas.v2.MapAction",
                            "index": (parseInt(separatorIndex) - 1)
                        }
                    ]
                };
            }
            //FIXME: this is a hack until we can pass proper path into mapping service
            field.serviceObject.name = field.path;
            var fieldJson = {
                "jsonType": "com.mediadriver.atlas.v2.MappedField",
                "field": field.serviceObject,
                "fieldActions": mappingFieldActions
            };
            fieldsJson.push(fieldJson);
        }
        return fieldsJson;
    };
    MappingSerializer.deserializeMappings = function (jsonMappings) {
        var mappings = [];
        for (var _i = 0, jsonMappings_1 = jsonMappings; _i < jsonMappings_1.length; _i++) {
            var jsonMapping = jsonMappings_1[_i];
            for (var _a = 0, _b = jsonMapping.AtlasMapping.fieldMappings.fieldMapping; _a < _b.length; _a++) {
                var fieldMapping = _b[_a];
                var m = MappingSerializer.createMapping();
                m.saved = true;
                var isSeparateMapping = (fieldMapping.jsonType == "com.mediadriver.atlas.v2.SeparateFieldMapping");
                m.transition.mode = isSeparateMapping ? transition_model_1.TransitionMode.SEPARATE : transition_model_1.TransitionMode.MAP;
                //TODO: only supporting one input field right now.
                var f = new field_model_1.Field();
                f.name = fieldMapping.inputField.field.name;
                f.type = fieldMapping.inputField.field.type;
                //TODO: calculate proper path
                f.path = f.name;
                f.serviceObject = fieldMapping.inputField.field;
                MappingSerializer.addFieldIfDoesntExist(m.inputFieldPaths, f.path);
                if (isSeparateMapping) {
                    var d = transition_model_1.TransitionDelimiter.COMMA;
                    d = (fieldMapping.strategy == "SPACE") ? transition_model_1.TransitionDelimiter.SPACE :
                        m.transition.delimiter = d;
                    var delimeter = (m.transition.delimiter == transition_model_1.TransitionDelimiter.SPACE) ? "SPACE" : "COMMA";
                    for (var _c = 0, _d = fieldMapping.outputFields.mappedField; _c < _d.length; _c++) {
                        var outputField = _d[_c];
                        f = new field_model_1.Field();
                        f.name = outputField.field.name;
                        f.type = outputField.field.type;
                        if (outputField.fieldActions
                            && outputField.fieldActions.fieldAction.length
                            && outputField.fieldActions.fieldAction[0].index) {
                            var index = (outputField.fieldActions.fieldAction[0].index + 1);
                            m.fieldSeparatorIndexes[f.name] = index.toString();
                        }
                        else {
                            m.fieldSeparatorIndexes[f.name] = "1";
                        }
                        //TODO: calculate proper path
                        f.path = f.name;
                        f.serviceObject = outputField.field;
                        MappingSerializer.addFieldIfDoesntExist(m.outputFieldPaths, f.path);
                    }
                }
                else {
                    f = new field_model_1.Field();
                    f.name = fieldMapping.outputField.field.name;
                    f.type = fieldMapping.outputField.field.type;
                    //TODO: calculate proper path
                    f.path = f.name;
                    f.serviceObject = fieldMapping.outputField.field;
                    MappingSerializer.addFieldIfDoesntExist(m.outputFieldPaths, f.path);
                }
                mappings.push(m);
            }
        }
        return mappings;
    };
    MappingSerializer.addFieldIfDoesntExist = function (fieldPaths, fieldPath) {
        for (var _i = 0, fieldPaths_2 = fieldPaths; _i < fieldPaths_2.length; _i++) {
            var preexistingFieldPath = fieldPaths_2[_i];
            if (fieldPath == preexistingFieldPath) {
                return;
            }
        }
        fieldPaths.push(fieldPath);
    };
    MappingSerializer.createMapping = function () {
        var m = new mapping_model_1.MappingModel();
        m.uuid = "mapping #" + MappingSerializer.uuidCounter;
        MappingSerializer.uuidCounter++;
        return m;
    };
    MappingSerializer.uuidCounter = 0;
    return MappingSerializer;
}());
exports.MappingSerializer = MappingSerializer;
//# sourceMappingURL=mapping.serializer.js.map
/*
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
"use strict";
var DocumentDefinition = (function () {
    function DocumentDefinition() {
        this.fields = [];
        this.allFields = [];
        this.terminalFields = [];
        this.complexFieldsByClassName = {};
        this.enumFieldClasses = [];
        this.debugParsing = false;
        this.fieldsByPath = {};
    }
    DocumentDefinition.prototype.getCachedField = function (name) {
        return this.complexFieldsByClassName[name];
    };
    DocumentDefinition.prototype.getAllFields = function () {
        return [].concat(this.allFields);
    };
    DocumentDefinition.prototype.getFields = function (fieldPaths) {
        var fields = [];
        for (var _i = 0, fieldPaths_1 = fieldPaths; _i < fieldPaths_1.length; _i++) {
            var fieldPath = fieldPaths_1[_i];
            var field = this.getField(fieldPath);
            if (field != null) {
                fields.push(field);
            }
        }
        return fields;
    };
    DocumentDefinition.prototype.getField = function (fieldPath) {
        return this.fieldsByPath[fieldPath];
    };
    DocumentDefinition.prototype.getTerminalFields = function () {
        return [].concat(this.terminalFields);
    };
    DocumentDefinition.prototype.clearSelectedFields = function () {
        for (var _i = 0, _a = this.allFields; _i < _a.length; _i++) {
            var field = _a[_i];
            field.selected = false;
        }
    };
    DocumentDefinition.prototype.getSelectedFields = function () {
        var fields = [];
        for (var _i = 0, _a = this.allFields; _i < _a.length; _i++) {
            var field = _a[_i];
            if (field.selected) {
                fields.push(field);
            }
        }
        return fields;
    };
    DocumentDefinition.prototype.selectFields = function (fieldPaths) {
        for (var _i = 0, fieldPaths_2 = fieldPaths; _i < fieldPaths_2.length; _i++) {
            var fieldPath = fieldPaths_2[_i];
            var field = this.getField(fieldPath);
            if (field != null) {
                field.selected = true;
            }
        }
    };
    DocumentDefinition.prototype.populateFromFields = function () {
        //TODO: put enums back in after demo
        this.filterEnumFields(this.fields);
        this.populateComplexFields();
        this.alphabetizeFields(this.fields);
        for (var _i = 0, _a = this.fields; _i < _a.length; _i++) {
            var field = _a[_i];
            this.populateFieldParentPaths(field, "", 0);
            this.populateFieldData(field);
        }
        if (this.debugParsing) {
            console.log(this.printDocumentFields(this.fields, 0));
        }
        console.log("Finished populating fields for '" + this.name + "', field count: " + this.allFields.length + ", terminal: " + this.terminalFields.length + ".");
    };
    DocumentDefinition.prototype.alphabetizeFields = function (fields) {
        var fieldsByName = {};
        var fieldNames = [];
        for (var _i = 0, fields_1 = fields; _i < fields_1.length; _i++) {
            var field = fields_1[_i];
            var name = field.name;
            var firstCharacter = name.charAt(0).toUpperCase();
            name = firstCharacter + name.substring(1);
            field.displayName = name;
            //if field is a dupe, discard it
            if (fieldsByName[name] != null) {
                continue;
            }
            fieldsByName[name] = field;
            fieldNames.push(name);
        }
        fieldNames.sort();
        fields.length = 0;
        for (var _a = 0, fieldNames_1 = fieldNames; _a < fieldNames_1.length; _a++) {
            var name_1 = fieldNames_1[_a];
            fields.push(fieldsByName[name_1]);
        }
        for (var _b = 0, fields_2 = fields; _b < fields_2.length; _b++) {
            var field = fields_2[_b];
            if (field.children && field.children.length) {
                this.alphabetizeFields(field.children);
            }
        }
    };
    DocumentDefinition.prototype.populateFieldParentPaths = function (field, parentPath, depth) {
        field.path = parentPath + field.displayName;
        field.fieldDepth = depth;
        for (var _i = 0, _a = field.children; _i < _a.length; _i++) {
            var childField = _a[_i];
            childField.parentField = field;
            this.populateFieldParentPaths(childField, parentPath + field.displayName + ".", depth + 1);
        }
    };
    DocumentDefinition.prototype.populateFieldData = function (field) {
        this.allFields.push(field);
        this.fieldsByPath[field.path] = field;
        if (field.children.length == 0) {
            this.terminalFields.push(field);
        }
        else {
            for (var _i = 0, _a = field.children; _i < _a.length; _i++) {
                var childField = _a[_i];
                this.populateFieldData(childField);
            }
        }
    };
    DocumentDefinition.prototype.filterEnumFields = function (fields) {
        var fieldsCopy = [].concat(fields);
        fields.length = 0;
        for (var _i = 0, fieldsCopy_1 = fieldsCopy; _i < fieldsCopy_1.length; _i++) {
            var field = fieldsCopy_1[_i];
            if ((field.serviceObject.enumeration == true)
                || (this.enumFieldClasses.indexOf(field.serviceObject.className) != -1)) {
                console.log("Filtering out enum field: " + field.name + " (" + field.serviceObject.className + ")");
                continue;
            }
            fields.push(field);
            this.filterEnumFields(field.children);
        }
    };
    DocumentDefinition.prototype.populateComplexFields = function () {
        var fields = this.fields;
        //build complex field cache
        this.discoverComplexFields(fields);
        //remove children more than one level deep in cached fields
        for (var key in this.complexFieldsByClassName) {
            var cachedField = this.complexFieldsByClassName[key];
            for (var _i = 0, _a = cachedField.children; _i < _a.length; _i++) {
                var childField = _a[_i];
                childField.children = [];
            }
        }
        // print cached complex fields
        if (this.debugParsing) {
            var result = "Cached Fields: ";
            for (var key in this.complexFieldsByClassName) {
                var cachedField = this.complexFieldsByClassName[key];
                result += cachedField.name + " " + cachedField.type + " " + cachedField.serviceObject.status
                    + " (" + cachedField.serviceObject.className + ") children:" + cachedField.children.length + "\n";
            }
            console.log(result);
        }
        //remove children more than one layer deep in root fields
        for (var _b = 0, fields_3 = fields; _b < fields_3.length; _b++) {
            var field = fields_3[_b];
            for (var _c = 0, _d = field.children; _c < _d.length; _c++) {
                var childField = _d[_c];
                childField.children = [];
            }
        }
        //populate complex fields
        this.populateComplexFieldInternal(fields, 1);
    };
    DocumentDefinition.prototype.populateComplexFieldInternal = function (fields, depth) {
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            if (field.type != "COMPLEX") {
                continue;
            }
            var cachedField = this.getCachedField(field.serviceObject.className);
            if (cachedField == null) {
                console.error("ERROR: Couldn't find cached complex field: " + field.serviceObject.className);
                continue;
            }
            //copy cached field
            cachedField = cachedField.copy();
            cachedField.name = field.name;
            if (depth < 2) {
                //TODO: after demo fix recursion here
                this.populateComplexFieldInternal(cachedField.children, depth + 1);
            }
            fields[i] = cachedField;
        }
    };
    DocumentDefinition.prototype.discoverComplexFields = function (fields) {
        for (var _i = 0, fields_4 = fields; _i < fields_4.length; _i++) {
            var field = fields_4[_i];
            if (field.type != "COMPLEX") {
                continue;
            }
            if (field.serviceObject.status == "SUPPORTED") {
                this.complexFieldsByClassName[field.serviceObject.className] = field.copy();
            }
            if (field.children) {
                this.discoverComplexFields(field.children);
            }
        }
    };
    DocumentDefinition.prototype.printDocumentFields = function (fields, indent) {
        var result = "";
        for (var _i = 0, fields_5 = fields; _i < fields_5.length; _i++) {
            var f = fields_5[_i];
            if (f.type != "COMPLEX") {
                continue;
            }
            for (var i = 0; i < indent; i++) {
                result += "\t";
            }
            result += f.name + " " + f.type + " " + f.serviceObject.status + " (" + f.serviceObject.className + ") children:" + f.children.length;
            result += "\n";
            if (f.children) {
                result += this.printDocumentFields(f.children, indent + 1);
            }
        }
        return result;
    };
    DocumentDefinition.prototype.updateFromMappings = function (mappings) {
        for (var _i = 0, _a = this.allFields; _i < _a.length; _i++) {
            var field = _a[_i];
            field.partOfMapping = false;
        }
        for (var _b = 0, mappings_1 = mappings; _b < mappings_1.length; _b++) {
            var mapping = mappings_1[_b];
            var fieldPaths = this.isInput ? mapping.inputFieldPaths : mapping.outputFieldPaths;
            for (var _c = 0, _d = this.getFields(fieldPaths); _c < _d.length; _c++) {
                var field = _d[_c];
                field.partOfMapping = true;
                var parentField = field.parentField;
                while (parentField != null) {
                    parentField.partOfMapping = true;
                    parentField = parentField.parentField;
                }
            }
        }
    };
    return DocumentDefinition;
}());
exports.DocumentDefinition = DocumentDefinition;
//# sourceMappingURL=document.definition.model.js.map
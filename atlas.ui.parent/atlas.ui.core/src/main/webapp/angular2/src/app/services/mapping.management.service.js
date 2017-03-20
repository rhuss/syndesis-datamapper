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
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
require('rxjs/add/operator/toPromise');
var Rx_1 = require('rxjs/Rx');
require('rxjs/add/observable/forkJoin');
var Subject_1 = require('rxjs/Subject');
var mapping_serializer_1 = require('./mapping.serializer');
var MappingManagementService = (function () {
    function MappingManagementService(http) {
        this.http = http;
        this.mappingUpdatedSource = new Subject_1.Subject();
        this.mappingUpdated$ = this.mappingUpdatedSource.asObservable();
        this.activeMappingChangedSource = new Subject_1.Subject();
        this.activeMappingChanged$ = this.activeMappingChangedSource.asObservable();
        this.mappingSelectionRequiredSource = new Subject_1.Subject();
        this.mappingSelectionRequired$ = this.mappingSelectionRequiredSource.asObservable();
        this.headers = new http_1.Headers();
        this.headers.append("Content-Type", "application/json");
    }
    MappingManagementService.prototype.initialize = function () {
        var _this = this;
        console.log("Initializing mappings.");
        var startTime = Date.now();
        this.cfg.documentService.updateFromSelectedFields$.subscribe(function () {
            _this.fieldSelectionChanged();
        });
        var url = this.cfg.baseMappingServiceUrl + "mappings?filter=UI";
        this.http.get(url, { headers: this.headers }).toPromise()
            .then(function (res) { return _this.extractMappings(res, startTime); })
            .catch(function (error) { _this.handleError("Error occurred while retrieving mappings.", error); });
    };
    MappingManagementService.prototype.extractMappings = function (res, startTime) {
        var _this = this;
        var body = res.json();
        var entries = body.StringMap.stringMapEntry;
        var mappingNames = [];
        for (var _i = 0, entries_1 = entries; _i < entries_1.length; _i++) {
            var entry = entries_1[_i];
            mappingNames.push(entry.name);
        }
        if (mappingNames.length == 0) {
            console.log("No pre-existing mapping exists.");
            return;
        }
        var baseURL = this.cfg.baseMappingServiceUrl + "mapping/";
        var operations = [];
        for (var _a = 0, mappingNames_1 = mappingNames; _a < mappingNames_1.length; _a++) {
            var mappingName = mappingNames_1[_a];
            var url = baseURL + mappingName;
            var operation = this.http.get(url).map(function (res) { return res.json(); });
            operations.push(operation);
        }
        Rx_1.Observable.forkJoin(operations).subscribe(function (data) {
            if (!data) {
                console.log("No pre-existing mappings were found.");
                return;
            }
            console.log("Initializing from " + data.length + " fetched mappings.");
            _this.cfg.mappings.mappings = _this.cfg.mappings.mappings.concat(mapping_serializer_1.MappingSerializer.deserializeMappings(data));
            if (data && data.length) {
                _this.cfg.mappings.mappingUUID = data[0].AtlasMapping.name;
            }
            console.log("Finished loading mappings in " + (Date.now() - startTime) + "ms.");
            _this.mappingUpdatedSource.next();
        }, function (err) { _this.handleError("Error occurred while retrieving a mapping.", err); });
    };
    MappingManagementService.prototype.printMappings = function (reason) {
        var mappings = this.cfg.mappings.mappings;
        var msg = "Mapping status for '" + reason +
            "', current mapping count: " + mappings.length;
        for (var i = 0; i < mappings.length; i++) {
            msg += "\n\tMapping #" + i + ": " + this.printMapping(mappings[i]);
        }
        console.log(msg);
    };
    MappingManagementService.prototype.printMapping = function (m) {
        if (m == null) {
            return "[no mapping]";
        }
        var inputs = "";
        for (var _i = 0, _a = m.inputFieldPaths; _i < _a.length; _i++) {
            var fieldPath = _a[_i];
            inputs += fieldPath + ", ";
        }
        var outputs = "";
        for (var _b = 0, _c = m.outputFieldPaths; _b < _c.length; _b++) {
            var fieldPath = _c[_b];
            outputs += fieldPath + ", ";
        }
        return "uuid: " + m.uuid + ", inputs: {" + inputs + "}, outputs {" + outputs + "}.";
    };
    MappingManagementService.prototype.saveMapping = function (m) {
        var startTime = Date.now();
        console.log("Saving mapping: " + this.printMapping(m));
        this.removeMappingInternal(m);
        this.printMappings("Saved Mapping.");
        this.cfg.mappings.mappings.push(m);
        this.saveMappingToService(m);
    };
    MappingManagementService.prototype.saveMappingToService = function (m) {
        var _this = this;
        var startTime = Date.now();
        var payload = mapping_serializer_1.MappingSerializer.serializeMappings(this.cfg.mappings, this.cfg.inputDoc, this.cfg.outputDoc);
        var jsonVersion = JSON.stringify(payload);
        var jsonPretty = JSON.stringify(JSON.parse(jsonVersion), null, 2);
        console.log("JSON for saved mapping.", jsonPretty);
        var url = this.cfg.baseMappingServiceUrl + "mapping";
        this.http.put(url, jsonVersion, { headers: this.headers }).toPromise()
            .then(function (res) {
            m.saved = true;
            _this.deselectMapping();
            console.log("Saved mappings to service in " + (Date.now() - startTime) + "ms.");
            _this.mappingUpdatedSource.next();
        })
            .catch(function (error) { _this.handleError("Error occurred while saving mapping.", error); });
    };
    MappingManagementService.prototype.removeMapping = function (m) {
        console.log("Removing mapping: " + this.printMapping(m));
        this.removeMappingInternal(m);
        if (m.saved) {
            this.saveMappingToService(m);
        }
        else {
            this.deselectMapping();
        }
    };
    MappingManagementService.prototype.removeMappingInternal = function (m) {
        var mappings = this.cfg.mappings.mappings;
        for (var i = 0; i < mappings.length; i++) {
            if (mappings[i].uuid == m.uuid) {
                mappings.splice(i, 1);
                break;
            }
        }
    };
    MappingManagementService.prototype.findMappingsForField = function (fieldPath, isInput) {
        var mappingsForField = [];
        for (var _i = 0, _a = this.cfg.mappings.mappings; _i < _a.length; _i++) {
            var m = _a[_i];
            var fieldPaths = isInput ? m.inputFieldPaths : m.outputFieldPaths;
            for (var _b = 0, fieldPaths_1 = fieldPaths; _b < fieldPaths_1.length; _b++) {
                var currentFieldPath = fieldPaths_1[_b];
                if (currentFieldPath == fieldPath) {
                    mappingsForField.push(m);
                }
            }
        }
        return mappingsForField;
    };
    MappingManagementService.prototype.handleError = function (message, error) {
        if (error instanceof http_1.Response) {
            if (error.status == 230) {
                message += " (Connection refused)";
            }
            else if (error.status == 500) {
                message += " (Internal Server Error)";
            }
            else if (error.status == 404) {
                message += " (Not Found)";
            }
        }
        this.cfg.errorService.error(message, error);
    };
    MappingManagementService.prototype.getMappedFields = function (isInput) {
        var result = [];
        for (var _i = 0, _a = this.cfg.mappings.mappings; _i < _a.length; _i++) {
            var m = _a[_i];
            var fieldPaths = isInput ? m.inputFieldPaths : m.outputFieldPaths;
            for (var _b = 0, fieldPaths_2 = fieldPaths; _b < fieldPaths_2.length; _b++) {
                var fieldPath = fieldPaths_2[_b];
                result.push(fieldPath);
            }
        }
        return result;
    };
    MappingManagementService.prototype.removeMappedField = function (fieldPath, isInput) {
        var fieldPaths = (isInput ? this.cfg.mappings.activeMapping.inputFieldPaths
            : this.cfg.mappings.activeMapping.outputFieldPaths);
        for (var i = 0; i < fieldPaths.length; i++) {
            if (fieldPaths[i] == fieldPath) {
                fieldPaths.splice(i, 1);
                break;
            }
        }
        var field = isInput ? this.cfg.inputDoc.getField(fieldPath) : this.cfg.outputDoc.getField(fieldPath);
        field.selected = false;
        this.activeMappingChangedSource.next(false);
    };
    MappingManagementService.prototype.addMappedField = function (fieldPath, isInput) {
        var docDef = isInput ? this.cfg.inputDoc : this.cfg.outputDoc;
        fieldPath = (fieldPath == null) ? docDef.allFields[0].path : fieldPath;
        var fieldsPaths = (isInput ? this.cfg.mappings.activeMapping.inputFieldPaths
            : this.cfg.mappings.activeMapping.outputFieldPaths);
        fieldsPaths.push(fieldPath);
        var field = isInput ? docDef.getField(fieldPath) : docDef.getField(fieldPath);
        if (field != null) {
            field.selected = true;
        }
        this.cfg.mappings.activeMapping.updateSeparatorIndexes();
        this.activeMappingChangedSource.next(false);
    };
    MappingManagementService.prototype.createMapping = function () {
        return mapping_serializer_1.MappingSerializer.createMapping();
    };
    MappingManagementService.prototype.deselectMapping = function () {
        this.cfg.mappings.activeMapping = null;
        this.cfg.inputDoc.clearSelectedFields();
        this.cfg.outputDoc.clearSelectedFields();
        this.activeMappingChangedSource.next(false);
    };
    MappingManagementService.prototype.fieldSelectionChanged = function () {
        var mapping = this.cfg.mappings.activeMapping;
        this.printMapping(mapping);
        var mappingIsNew = false;
        var selectedInputFields = this.cfg.inputDoc.getSelectedFields();
        var selectedOutputFields = this.cfg.outputDoc.getSelectedFields();
        if (mapping == null) {
            if ((selectedInputFields.length == 0) && (selectedOutputFields.length == 0)) {
                //no mapping, exit
                this.selectMapping(mapping, false);
                return;
            }
            var fieldToFind = null;
            var isInput = true;
            if ((selectedInputFields.length == 1) && (selectedOutputFields.length == 0)) {
                fieldToFind = selectedInputFields[0];
            }
            if ((selectedInputFields.length == 0) && (selectedOutputFields.length == 1)) {
                fieldToFind = selectedOutputFields[0];
                isInput = false;
            }
            var mappingsForField = (fieldToFind == null) ? null
                : this.findMappingsForField(fieldToFind.path, isInput);
            if (mappingsForField && mappingsForField.length > 1) {
                console.log("Found " + mappingsForField.length + " existing mappings.");
                this.mappingSelectionRequiredSource.next(mappingsForField);
                return;
            }
            else if (mappingsForField && mappingsForField.length == 1) {
                console.log("Found existing mapping.");
                mapping = mappingsForField[0];
            }
            else if (mappingsForField == null || mappingsForField.length == 0) {
                console.log("Creating new mapping.");
                mappingIsNew = true;
                mapping = this.createMapping();
                mapping.inputFieldPaths = [].concat(this.getFieldPaths(selectedInputFields));
                mapping.outputFieldPaths = [].concat(this.getFieldPaths(selectedOutputFields));
            }
        }
        else {
            mapping.inputFieldPaths = [].concat(this.getFieldPaths(selectedInputFields));
            mapping.outputFieldPaths = [].concat(this.getFieldPaths(selectedOutputFields));
        }
        this.selectMapping(mapping, mappingIsNew);
    };
    MappingManagementService.prototype.selectMapping = function (m, mappingIsNew) {
        this.cfg.mappings.activeMapping = m;
        this.cfg.showMappingDetailTray = true;
        if (m == null) {
            this.cfg.inputDoc.clearSelectedFields();
            this.cfg.outputDoc.clearSelectedFields();
        }
        else {
            this.cfg.inputDoc.selectFields(m.inputFieldPaths);
            this.cfg.outputDoc.selectFields(m.outputFieldPaths);
        }
        this.activeMappingChangedSource.next(mappingIsNew);
    };
    MappingManagementService.prototype.getFieldPaths = function (fields) {
        var paths = [];
        for (var _i = 0, fields_1 = fields; _i < fields_1.length; _i++) {
            var field = fields_1[_i];
            paths.push(field.path);
        }
        return paths;
    };
    MappingManagementService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], MappingManagementService);
    return MappingManagementService;
}());
exports.MappingManagementService = MappingManagementService;
//# sourceMappingURL=mapping.management.service.js.map
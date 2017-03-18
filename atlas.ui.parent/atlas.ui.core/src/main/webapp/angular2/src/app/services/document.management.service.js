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
require('rxjs/add/observable/forkJoin');
var Subject_1 = require('rxjs/Subject');
var field_model_1 = require('../models/field.model');
var document_definition_model_1 = require('../models/document.definition.model');
var DocumentManagementService = (function () {
    function DocumentManagementService(http) {
        this.http = http;
        this.debugParsing = false;
        this.updateFromSelectedFieldsSource = new Subject_1.Subject();
        this.updateFromSelectedFields$ = this.updateFromSelectedFieldsSource.asObservable();
        this.documentsFetchedSource = new Subject_1.Subject();
        this.documentsFetched$ = this.documentsFetchedSource.asObservable();
        this.headers = new http_1.Headers();
        this.headers.append("Content-Type", "application/json");
    }
    DocumentManagementService.prototype.updateSearch = function (searchFilter, isInput) {
        var docDef = isInput ? this.cfg.inputDoc : this.cfg.outputDoc;
        for (var _i = 0, _a = docDef.getAllFields(); _i < _a.length; _i++) {
            var field = _a[_i];
            field.visible = false;
        }
        for (var _b = 0, _c = docDef.getTerminalFields(); _b < _c.length; _b++) {
            var field = _c[_b];
            this.updateSearchVisibilityForField(searchFilter, field);
        }
        this.notifyUpdateFromSelectedFields();
    };
    DocumentManagementService.prototype.updateSearchVisibilityForField = function (searchFilter, field) {
        field.visible = (searchFilter == null || "" == searchFilter || field.name.includes(searchFilter));
        if (field.visible) {
            var parentField = field.parentField;
            while (parentField != null) {
                parentField.visible = true;
                parentField = parentField.parentField;
            }
        }
    };
    DocumentManagementService.prototype.initialize = function () {
        var _this = this;
        //fetch input doc
        var startTime = Date.now();
        var className = this.cfg.mappingInputJavaClass;
        var url = this.cfg.baseJavaServiceUrl + "class?className=" + className;
        this.http.get(url, { headers: this.headers }).toPromise()
            .then(function (res) { _this.extractDocumentDefinitionData(res, true, startTime); })
            .catch(function (error) { _this.handleError("Error occurred while retrieving document fields.", error); });
        //fetch output doc
        var className = this.cfg.mappingOutputJavaClass;
        var url = this.cfg.baseJavaServiceUrl + "class?className=" + className;
        this.http.get(url, { headers: this.headers }).toPromise()
            .then(function (res) { _this.extractDocumentDefinitionData(res, false, startTime); })
            .catch(function (error) { _this.handleError("Error occurred while retrieving document fields.", error); });
        this.cfg.mappingService.mappingUpdated$.subscribe(function (mappingDefinition) {
            _this.cfg.inputDoc.updateFromMappings(_this.cfg.mappings.mappings);
            _this.cfg.outputDoc.updateFromMappings(_this.cfg.mappings.mappings);
        });
    };
    DocumentManagementService.prototype.extractDocumentDefinitionData = function (res, isInput, startTime) {
        var body = res.json();
        var docDef = new document_definition_model_1.DocumentDefinition();
        docDef.isInput = isInput;
        docDef.name = body.JavaClass.fullyQualifiedName;
        docDef.debugParsing = this.debugParsing;
        console.log("Loading document: " + docDef.name);
        if (this.debugParsing) {
            console.log("Document JSON from Service.", body);
            var jsonPretty = JSON.stringify(body, null, 2);
            console.log("Pretty formatted JSON.", jsonPretty);
        }
        if (body.JavaClass.status == "NOT_FOUND") {
            this.handleError("Could not load document. Document is not found: " + docDef.name, null);
            return;
        }
        var fields = docDef.fields;
        for (var _i = 0, _a = body.JavaClass.javaFields.javaField; _i < _a.length; _i++) {
            var field = _a[_i];
            var parsedField = this.parseFieldFromDocument(field, docDef);
            if (parsedField != null) {
                fields.push(parsedField);
            }
        }
        docDef.populateFromFields();
        if (isInput) {
            this.cfg.inputDoc = docDef;
        }
        else {
            this.cfg.outputDoc = docDef;
        }
        console.log("Finished fetching and parsing document '" + docDef.name + "' in " + (Date.now() - startTime) + "ms.");
        this.documentsFetchedSource.next();
    };
    DocumentManagementService.prototype.parseFieldFromDocument = function (field, docDef) {
        if (field != null && field.status == "NOT_FOUND") {
            console.error("Filtering missing field: " + field.name + " (" + field.className
                + "), parent class: " + docDef.name);
            return null;
        }
        else if (field != null && field.enumeration == true) {
            docDef.enumFieldClasses.push(field.className);
            return null;
        }
        var parsedField = new field_model_1.Field();
        parsedField.name = field.name;
        parsedField.type = field.type;
        if (field.javaFields && field.javaFields.javaField && field.javaFields.javaField.length) {
            for (var _i = 0, _a = field.javaFields.javaField; _i < _a.length; _i++) {
                var childField = _a[_i];
                var parsedChild = this.parseFieldFromDocument(childField, docDef);
                if (parsedChild != null) {
                    parsedField.children.push(parsedChild);
                }
            }
        }
        parsedField.serviceObject = field;
        var className = field.className;
        //temp fixes for missing twitter4j classes
        if (className == "User") {
            className = "twitter4j.User";
        }
        else if (className == "URLEntity") {
            className = "twitter4j.URLEntity";
        }
        else if (className == "Scopes") {
            className = "twitter4j.Scopes";
        }
        else if (className == "Status") {
            className = "twitter4j.Status";
        }
        else if (className == "GeoLocation") {
            className = "twitter4j.GeoLocation";
        }
        else if (className == "Place") {
            className = "twitter4j.Place";
        }
        field.className = className;
        return parsedField;
    };
    DocumentManagementService.prototype.notifyUpdateFromSelectedFields = function () {
        this.updateFromSelectedFieldsSource.next();
    };
    DocumentManagementService.prototype.handleError = function (message, error) {
        if (error != null && error instanceof http_1.Response) {
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
    DocumentManagementService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], DocumentManagementService);
    return DocumentManagementService;
}());
exports.DocumentManagementService = DocumentManagementService;
//# sourceMappingURL=document.management.service.js.map
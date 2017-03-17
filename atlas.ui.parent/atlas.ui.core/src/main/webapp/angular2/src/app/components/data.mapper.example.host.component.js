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
var mapping_definition_model_1 = require('../models/mapping.definition.model');
var config_model_1 = require('../models/config.model');
var error_handler_service_1 = require('../services/error.handler.service');
var document_management_service_1 = require('../services/document.management.service');
var mapping_management_service_1 = require('../services/mapping.management.service');
var data_mapper_app_component_1 = require('./data.mapper.app.component');
var DataMapperAppExampleHostComponent = (function () {
    function DataMapperAppExampleHostComponent(documentService, mappingService, errorService) {
        var _this = this;
        this.documentService = documentService;
        this.mappingService = mappingService;
        this.errorService = errorService;
        var c = new config_model_1.ConfigModel();
        c.baseJavaServiceUrl = "http://localhost:8585/v2/atlas/java/";
        c.baseMappingServiceUrl = "http://localhost:8585/v2/atlas/";
        c.mappingInputJavaClass = "twitter4j.Status";
        c.mappingOutputJavaClass = "org.apache.camel.salesforce.dto.Contact";
        c.mappings = new mapping_definition_model_1.MappingDefinition();
        c.documentService = documentService;
        c.mappingService = mappingService;
        c.errorService = errorService;
        this.cfg = c;
        c.documentService.cfg = c;
        c.mappingService.cfg = c;
        c.documentService.documentsFetched$.subscribe(function () {
            _this.dataMapperComponent.updateFromConfig();
        });
        c.documentService.initialize();
        c.mappingService.initialize();
        c.mappingService.mappingUpdated$.subscribe(function (mappingDefinition) {
            console.log("Data Mapper is updating mapping definition.", mappingDefinition);
        });
    }
    __decorate([
        core_1.ViewChild('dataMapperComponent'), 
        __metadata('design:type', data_mapper_app_component_1.DataMapperAppComponent)
    ], DataMapperAppExampleHostComponent.prototype, "dataMapperComponent", void 0);
    DataMapperAppExampleHostComponent = __decorate([
        core_1.Component({
            selector: 'data-mapper-example-host',
            template: "\n  \t<data-mapper #dataMapperComponent [cfg]=\"cfg\"></data-mapper>\n  ",
            providers: [mapping_management_service_1.MappingManagementService, error_handler_service_1.ErrorHandlerService, document_management_service_1.DocumentManagementService]
        }), 
        __metadata('design:paramtypes', [document_management_service_1.DocumentManagementService, mapping_management_service_1.MappingManagementService, error_handler_service_1.ErrorHandlerService])
    ], DataMapperAppExampleHostComponent);
    return DataMapperAppExampleHostComponent;
}());
exports.DataMapperAppExampleHostComponent = DataMapperAppExampleHostComponent;
//# sourceMappingURL=data.mapper.example.host.component.js.map
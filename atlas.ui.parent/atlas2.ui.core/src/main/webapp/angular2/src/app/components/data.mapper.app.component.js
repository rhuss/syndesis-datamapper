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
var config_model_1 = require('../models/config.model');
var document_definition_component_1 = require('./document.definition.component');
var mapping_detail_component_1 = require('./mapping.detail.component');
var modal_window_component_1 = require('./modal.window.component');
var data_mapper_error_component_1 = require('./data.mapper.error.component');
var line_machine_component_1 = require('./line.machine.component');
var mapping_selection_component_1 = require('./mapping.selection.component');
var toolbar_component_1 = require('./toolbar.component');
var DataMapperAppComponent = (function () {
    function DataMapperAppComponent() {
    }
    DataMapperAppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.toolbarComponent.parentComponent = this;
        this.mappingDetailComponent.modalWindow = this.modalWindow;
        this.cfg.mappingService.mappingSelectionRequired$.subscribe(function (mappings) {
            _this.selectMapping(mappings);
        });
    };
    DataMapperAppComponent.prototype.selectMapping = function (mappingsForField) {
        this.modalWindow.reset();
        this.modalWindow.parentComponent = this;
        this.modalWindow.headerText = "Select Mapping";
        this.modalWindow.nestedComponentInitializedCallback = function (mw) {
            var self = mw.parentComponent;
            var c = mw.nestedComponent;
            c.mappings = mappingsForField;
            c.selectedMapping = mappingsForField[0];
        };
        this.modalWindow.nestedComponentType = mapping_selection_component_1.MappingSelectionComponent;
        this.modalWindow.okButtonHandler = function (mw) {
            var self = mw.parentComponent;
            var c = mw.nestedComponent;
            var mapping = c.selectedMapping;
            self.cfg.mappingService.selectMapping(mapping, false);
        };
        this.modalWindow.cancelButtonHandler = function (mw) {
            var self = mw.parentComponent;
            self.cfg.mappingService.selectMapping(null, false);
        };
        this.modalWindow.show();
    };
    DataMapperAppComponent.prototype.updateFromConfig = function () {
        var _this = this;
        this.lineMachine.updateHeight();
        this.mappingDetailComponent.updateHeight();
        // update the mapping line drawing after our fields have redrawn themselves
        // without this, the x/y from the field dom elements is messed up / misaligned.
        setTimeout(function () { _this.lineMachine.redrawLinesForMappings(); }, 1);
    };
    DataMapperAppComponent.prototype.buttonClickedHandler = function (action, component) {
        var self = component.parentComponent;
        if ("add" == action) {
            console.log("Creating new mapping.");
            self.cfg.mappings.activeMapping = self.cfg.mappingService.createMapping();
            self.cfg.showMappingDetailTray = true;
        }
        else if ("showDetails" == action) {
            self.cfg.showMappingDetailTray = !self.cfg.showMappingDetailTray;
        }
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], DataMapperAppComponent.prototype, "cfg", void 0);
    __decorate([
        core_1.ViewChild('lineMachine'), 
        __metadata('design:type', line_machine_component_1.LineMachineComponent)
    ], DataMapperAppComponent.prototype, "lineMachine", void 0);
    __decorate([
        core_1.ViewChild('errorPanel'), 
        __metadata('design:type', data_mapper_error_component_1.DataMapperErrorComponent)
    ], DataMapperAppComponent.prototype, "errorPanel", void 0);
    __decorate([
        core_1.ViewChild('modalWindow'), 
        __metadata('design:type', modal_window_component_1.ModalWindowComponent)
    ], DataMapperAppComponent.prototype, "modalWindow", void 0);
    __decorate([
        core_1.ViewChild('docDefInput'), 
        __metadata('design:type', document_definition_component_1.DocumentDefinitionComponent)
    ], DataMapperAppComponent.prototype, "docDefInput", void 0);
    __decorate([
        core_1.ViewChild('docDefOutput'), 
        __metadata('design:type', document_definition_component_1.DocumentDefinitionComponent)
    ], DataMapperAppComponent.prototype, "docDefOutput", void 0);
    __decorate([
        core_1.ViewChild('mappingDetailComponent'), 
        __metadata('design:type', mapping_detail_component_1.MappingDetailComponent)
    ], DataMapperAppComponent.prototype, "mappingDetailComponent", void 0);
    __decorate([
        core_1.ViewChild('toolbarComponent'), 
        __metadata('design:type', toolbar_component_1.ToolbarComponent)
    ], DataMapperAppComponent.prototype, "toolbarComponent", void 0);
    DataMapperAppComponent = __decorate([
        core_1.Component({
            selector: 'data-mapper',
            template: "\n  \t<toolbar [buttonClickedHandler]=\"buttonClickedHandler\" #toolbarComponent [cfg]=\"cfg\"></toolbar>\n  \t<div style='height:100%;'>\n  \t\t<div class=\"row\"><data-mapper-error #errorPanel [errorService]=\"cfg.errorService\"></data-mapper-error></div>\n  \t\t<div class=\"row\">\n\t  \t\t<div class=\"col-md-12\"><modal-window #modalWindow></modal-window></div>\n  \t\t</div>\n  \t\t<div class=\"row\" style='height:100%;'>\n\t  \t\t<div class=\"col-md-9\">  \t\t\n\t  \t\t\t<div style=\"float:left; width:40%; padding-left:10px;\">\n\t\t  \t\t\t<document-definition #docDefInput [cfg]=\"cfg\"\n\t\t  \t\t\t\t[docDef]=\"cfg.inputDoc\" [lineMachine]=\"lineMachine\"></document-definition>\n\t\t  \t\t</div>\n\t\t  \t\t<div style=\"float:left; width:20%; margin-top:20px\">\n\t\t  \t\t\t<line-machine #lineMachine [cfg]=\"cfg\" \n\t\t  \t\t\t\t[docDefInput]=\"docDefInput\" [docDefOutput]=\"docDefOutput\"></line-machine>\n\t\t  \t\t</div>\n\t\t  \t\t<div style=\"float:left; width:40%; padding-right:10px;\">\n\t\t  \t\t\t<document-definition #docDefOutput [cfg]=\"cfg\"\n\t\t  \t\t\t\t[docDef]=\"cfg.outputDoc\" [lineMachine]=\"lineMachine\"></document-definition>\n\t\t  \t\t</div>\n\t\t  \t\t<div style=\"clear:both; height:0px;\">&nbsp;</div>\n\t\t  \t</div>\n\t\t  \t<div class=\"col-md-3\" style=\"padding:0px;\">\n\t\t  \t\t<mapping-detail #mappingDetailComponent [cfg]=\"cfg\"></mapping-detail>\n\t\t  \t</div>\n\t\t </div>\n  \t</div>\n  "
        }), 
        __metadata('design:paramtypes', [])
    ], DataMapperAppComponent);
    return DataMapperAppComponent;
}());
exports.DataMapperAppComponent = DataMapperAppComponent;
//# sourceMappingURL=data.mapper.app.component.js.map
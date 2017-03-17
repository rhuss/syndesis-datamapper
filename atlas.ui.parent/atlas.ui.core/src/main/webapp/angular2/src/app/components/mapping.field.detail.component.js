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
var document_definition_model_1 = require('../models/document.definition.model');
var MappingFieldDetailComponent = (function () {
    function MappingFieldDetailComponent() {
    }
    MappingFieldDetailComponent.prototype.getFieldDisplay = function (field) {
        return this.cfg.showMappingDataType ? field.path : (field.path + " (" + field.type + ")");
    };
    MappingFieldDetailComponent.prototype.remove = function (event) {
        this.cfg.mappingService.removeMappedField(this.selectedFieldPath, this.docDef.isInput);
    };
    MappingFieldDetailComponent.prototype.selectionChanged = function (event) {
        this.cfg.mappingService.removeMappedField(this.selectedFieldPath, this.docDef.isInput);
        var eventTarget = event.target; //extract this to avoid compiler error about 'selectedOptions' not existing.	
        this.selectedFieldPath = eventTarget.selectedOptions.item(0).attributes.getNamedItem("fieldPath").value;
        this.cfg.mappingService.addMappedField(this.selectedFieldPath, this.docDef.isInput);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], MappingFieldDetailComponent.prototype, "cfg", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], MappingFieldDetailComponent.prototype, "selectedFieldPath", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', document_definition_model_1.DocumentDefinition)
    ], MappingFieldDetailComponent.prototype, "docDef", void 0);
    MappingFieldDetailComponent = __decorate([
        core_1.Component({
            selector: 'mapping-field-detail',
            template: "\n\t  \t<div class='fieldDetail' *ngIf=\"docDef && docDef.fields\" style=\"margin-bottom:5px;\">\n\t  \t\t<select style=\"width:94%; float:left;\" [ngModel]=\"selectedFieldPath\" (change)=\"selectionChanged($event)\">\n      \t\t\t<option *ngFor=\"let f of docDef.getTerminalFields()\" [ngValue]=\"f.path\" [attr.fieldPath]=\"f.path\">{{getFieldDisplay(f)}}</option>\n   \t\t\t </select>\n   \t\t\t<a style='display:inline; float:right;' (click)=\"remove($event)\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>\n   \t\t\t<div style=\"clear:both; height:0px;\">&nbsp;</div>\n\t  \t</div>\n    "
        }), 
        __metadata('design:paramtypes', [])
    ], MappingFieldDetailComponent);
    return MappingFieldDetailComponent;
}());
exports.MappingFieldDetailComponent = MappingFieldDetailComponent;
//# sourceMappingURL=mapping.field.detail.component.js.map
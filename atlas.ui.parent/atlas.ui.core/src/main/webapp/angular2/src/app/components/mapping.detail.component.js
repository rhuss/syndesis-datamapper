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
var platform_browser_1 = require('@angular/platform-browser');
var config_model_1 = require('../models/config.model');
var modal_window_component_1 = require('./modal.window.component');
var transition_selection_component_1 = require('./transition.selection.component');
var MappingDetailComponent = (function () {
    function MappingDetailComponent(sanitizer) {
        this.sanitizer = sanitizer;
    }
    MappingDetailComponent.prototype.getMappingFields = function (isInput) {
        var docDef = isInput ? this.cfg.inputDoc : this.cfg.outputDoc;
        var fieldPaths = isInput ? this.cfg.mappings.activeMapping.inputFieldPaths
            : this.cfg.mappings.activeMapping.outputFieldPaths;
        return docDef.getFields(fieldPaths);
    };
    MappingDetailComponent.prototype.deselectMapping = function (event) {
        this.cfg.mappingService.deselectMapping();
    };
    MappingDetailComponent.prototype.toggleDataTypeVisibility = function (event) {
        this.cfg.showMappingDataType = !this.cfg.showMappingDataType;
    };
    MappingDetailComponent.prototype.saveMapping = function (event) {
        this.cfg.mappingService.saveMapping(this.cfg.mappings.activeMapping);
    };
    MappingDetailComponent.prototype.removeMapping = function (event) {
        this.cfg.mappingService.removeMapping(this.cfg.mappings.activeMapping);
    };
    MappingDetailComponent.prototype.addField = function (event, isInput) {
        this.cfg.mappingService.addMappedField(null, isInput);
    };
    MappingDetailComponent.prototype.editAction = function (event) {
        this.modalWindow.reset();
        this.modalWindow.parentComponent = this;
        this.modalWindow.headerText = "Configure Action";
        var m = this.cfg.mappings.activeMapping;
        this.modalWindow.nestedComponentInitializedCallback = function (mw) {
            var c = mw.nestedComponent;
            c.model.mode = m.transition.mode;
            c.model.delimiter = m.transition.delimiter;
        };
        this.modalWindow.nestedComponentType = transition_selection_component_1.TransitionSelectionComponent;
        this.modalWindow.okButtonHandler = function (mw) {
            var c = mw.nestedComponent;
            m.transition.mode = c.model.mode;
            m.transition.delimiter = c.model.delimiter;
            m.updateSeparatorIndexes();
        };
        this.modalWindow.show();
    };
    MappingDetailComponent.prototype.updateHeight = function () {
        if (this.cfg.inputDoc && this.cfg.outputDoc) {
            var maxFieldCount = Math.max(this.cfg.inputDoc.fields.length, this.cfg.outputDoc.fields.length);
            var heightCSS = ((maxFieldCount * 40) + 120).toString() + "px";
            this.detailStyle = this.sanitizer.bypassSecurityTrustStyle("height:" + heightCSS + ";");
        }
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], MappingDetailComponent.prototype, "cfg", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', modal_window_component_1.ModalWindowComponent)
    ], MappingDetailComponent.prototype, "modalWindow", void 0);
    __decorate([
        core_1.ViewChildren('mappingField'), 
        __metadata('design:type', core_1.QueryList)
    ], MappingDetailComponent.prototype, "mappingFields", void 0);
    MappingDetailComponent = __decorate([
        core_1.Component({
            selector: 'mapping-detail',
            template: "\n\t  \t<div class='fieldMappingDetail'  [attr.style]=\"detailStyle\" \n\t  \t\t*ngIf=\"cfg.mappings.activeMapping && cfg.showMappingDetailTray\">\n\t  \t\t<div class=\"card-pf-title\" style=\"margin:0px;\">\n\t  \t\t\t<div style=\"float:left;\"><h2 style=\"display:inline;\">Data Transformation</h2></div>\n\t  \t\t\t<div style=\"float:right; text-align:right; padding-right:2px;\">\n\t  \t\t\t<a (click)=\"removeMapping($event)\" style=\"font-size:14px;\" tooltip=\"Remove current mapping\">\n\t  \t\t\t\t<i class=\"fa fa-trash\" aria-hidden=\"true\" style=\"font-size:14px; margin-left:5px;\"></i> \n\t  \t\t\t</a>\n\t  \t\t\t<a (click)=\"toggleDataTypeVisibility($event)\" tooltip=\"Show field data types\" style=\"margin-left:5px;\">\n\t  \t\t\t\t<i style=\"font-size:14px;\" class=\"fa fa-cog\" aria-hidden=\"true\"></i>\n\t  \t\t\t</a>\n\t  \t\t\t<a (click)=\"deselectMapping($event)\" tooltip=\"Deselect current mapping\" style=\"margin-left:5px;\">\n\t  \t\t\t\t<i style=\"font-size:16px;\" class=\"fa fa-close\" aria-hidden=\"true\"></i>\n\t  \t\t\t</a>\t  \t\n\t  \t\t\t</div>\n\t  \t\t\t<div style=\"clear:both; height:0px;\"></div>\n\t  \t\t</div>\n\t  \t\t<div class=\"mappingFieldContainer\">\n\t\t  \t\t<h3 style=\"font-size:12px; font-weight:bold;\">Source</h3>\n\t\t  \t\t<div *ngFor=\"let field of getMappingFields(true)\" style=\"padding-bottom:10px;\">\n\t\t  \t\t\t<mapping-field-detail #mappingField [selectedFieldPath]=\"field.path\" [cfg]=\"cfg\"\n\t\t  \t\t\t\t[docDef]=\"cfg.inputDoc\"></mapping-field-detail>\n\t\t  \t\t\t<mapping-field-action [field]=\"field\" [mapping]=\"cfg.mappings.activeMapping\" \n\t\t  \t\t\t\t[isInput]=\"true\"></mapping-field-action>\n\t\t  \t\t</div>\n\t\t\t\t<!-- <a (click)=\"addField($event, true)\"><i class=\"fa fa-plus\" \n\t\t\t\t\taria-hidden=\"true\" style=\"font-size:10px\"></i> Add Field</a> -->\n\t\t\t</div>\n\t  \t\t<div class=\"mappingFieldContainer\" >\n\t  \t\t\t<label class=\"sectionHeader\" style=\"float:left; margin-bottom:0px;\">Action:&nbsp;</label>\n\t  \t\t\t<label style=\"float:left; margin-bottom:0px;\">\n\t  \t\t\t\t{{cfg.mappings.activeMapping.transition.getPrettyName()}}\n\t  \t\t\t</label>\n\t  \t\t\t<a style='display:inline; float:right; margin-right:1px' (click)=\"editAction($event)\">\n\t  \t\t\t\t<i class=\"fa fa-edit\" aria-hidden=\"true\"></i>\n\t  \t\t\t</a>\n\t  \t\t\t<div style=\"clear:both; height:0px;\">&nbsp;</div>\n\t  \t\t</div>\n\t  \t\t<div class=\"mappingFieldContainer\">\n\t\t  \t\t<h3 style=\"font-size:12px; font-weight:bold;\">Target</h3>\n\t\t  \t\t<div *ngFor=\"let field of getMappingFields(false)\" style=\"padding-bottom:10px;\">\n\t\t  \t\t\t<mapping-field-detail #mappingField [selectedFieldPath]=\"field.path\" [cfg]=\"cfg\"\n\t\t  \t\t\t\t[docDef]=\"cfg.outputDoc\"></mapping-field-detail>\n\t\t  \t\t\t<mapping-field-action [field]=\"field\" [mapping]=\"cfg.mappings.activeMapping\" \n\t\t  \t\t\t\t[isInput]=\"false\"></mapping-field-action>\n\t\t  \t\t</div>\n\t\t\t\t<a (click)=\"addField($event, false)\"><i class=\"fa fa-plus\" \n\t\t\t\t\taria-hidden=\"true\" style=\"font-size:10px\"></i> Add Field</a>\t  \t\t\n\t\t\t</div>\t\t  \t\t\t\t  \t\t\n\t  \t\t<a class='button' (click)=\"saveMapping($event)\"><i class=\"fa fa-save\" aria-hidden=\"true\"></i> Save</a>\n\t    </div>\n    "
        }), 
        __metadata('design:paramtypes', [platform_browser_1.DomSanitizer])
    ], MappingDetailComponent);
    return MappingDetailComponent;
}());
exports.MappingDetailComponent = MappingDetailComponent;
//# sourceMappingURL=mapping.detail.component.js.map
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
var document_definition_model_1 = require('../models/document.definition.model');
var line_machine_component_1 = require('./line.machine.component');
var DocumentDefinitionComponent = (function () {
    function DocumentDefinitionComponent(sanitizer) {
        this.sanitizer = sanitizer;
        this.searchMode = false;
        this.searchFilter = "";
    }
    DocumentDefinitionComponent.prototype.getFieldCount = function () {
        if (this.docDef && this.docDef.allFields) {
            return this.docDef.allFields.length;
        }
        return 0;
    };
    DocumentDefinitionComponent.prototype.getFieldDetailComponent = function (fieldPath) {
        for (var _i = 0, _a = this.fieldComponents.toArray(); _i < _a.length; _i++) {
            var c = _a[_i];
            var returnedComponent = c.getFieldDetailComponent(fieldPath);
            if (returnedComponent != null) {
                return returnedComponent;
            }
        }
        return null;
    };
    DocumentDefinitionComponent.prototype.getElementPosition = function () {
        var x = 0;
        var y = 0;
        var el = this.documentDefinitionElement.nativeElement;
        while (el != null) {
            x += el.offsetLeft;
            y += el.offsetTop;
            el = el.offsetParent;
        }
        return { "x": x, "y": y };
    };
    DocumentDefinitionComponent.prototype.getFieldDetailComponentPosition = function (fieldPath) {
        var c = this.getFieldDetailComponent(fieldPath);
        var fieldElementAbsPosition = c.getElementPosition();
        var myAbsPosition = this.getElementPosition();
        return { "x": (fieldElementAbsPosition.x - myAbsPosition.x), "y": (fieldElementAbsPosition.y - myAbsPosition.y) };
    };
    DocumentDefinitionComponent.prototype.search = function (searchFilter) {
        this.cfg.documentService.updateSearch(searchFilter, this.docDef.isInput);
    };
    DocumentDefinitionComponent.prototype.clearSearch = function () {
        this.cfg.documentService.updateSearch(null, this.docDef.isInput);
        this.searchFilter = "";
    };
    DocumentDefinitionComponent.prototype.toggleSearch = function () {
        this.searchMode = !this.searchMode;
        if (!this.searchMode) {
            this.clearSearch();
        }
        this.searchIconStyle = !this.searchMode ? null
            : this.sanitizer.bypassSecurityTrustStyle("color:#5CBADF;");
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], DocumentDefinitionComponent.prototype, "cfg", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', document_definition_model_1.DocumentDefinition)
    ], DocumentDefinitionComponent.prototype, "docDef", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', line_machine_component_1.LineMachineComponent)
    ], DocumentDefinitionComponent.prototype, "lineMachine", void 0);
    __decorate([
        core_1.ViewChild('documentDefinitionElement'), 
        __metadata('design:type', core_1.ElementRef)
    ], DocumentDefinitionComponent.prototype, "documentDefinitionElement", void 0);
    __decorate([
        core_1.ViewChildren('fieldDetail'), 
        __metadata('design:type', core_1.QueryList)
    ], DocumentDefinitionComponent.prototype, "fieldComponents", void 0);
    DocumentDefinitionComponent = __decorate([
        core_1.Component({
            selector: 'document-definition',
            template: "\n\t  \t<div #documentDefinitionElement class='docDef' *ngIf=\"docDef\">\n\t  \t\t<div class=\"card-pf\">\n\t  \t\t\t<div class=\"card-pf-heading\">\n    \t\t\t\t<h2 class=\"card-pf-title\">{{docDef.name}}</h2>\n                    <a class=\"searchBoxIcon\" (click)=\"toggleSearch()\">\n                        <i class=\"fa fa-search\" [attr.style]=\"searchIconStyle\"></i>\n                    </a>\n    \t\t\t</div>\n    \t\t\t<div class=\"card-pf-body\">\n                    <div *ngIf=\"searchMode\">\n                        <input type=\"text\" class=\"searchBox\" #searchFilterBox \n                            id=\"search-filter-box\" (keyup)=\"search(searchFilterBox.value)\" placeholder=\"Search\"\n                            [(ngModel)]=\"searchFilter\" />\n                        <a class=\"searchBoxCloseIcon\" (click)=\"clearSearch()\"><i class=\"fa fa-close\"></i></a>\n                    </div>\n                    <document-field-detail #fieldDetail *ngFor=\"let f of docDef.fields\" \n                        [field]=\"f\" [docDef]=\"docDef\" [cfg]=\"cfg\" \n                        [lineMachine]=\"lineMachine\"></document-field-detail>\n\t\t\t    </div>\n                <div class=\"card-pf-heading fieldsCount\">{{getFieldCount()}} fields</div>\n\t\t    </div>\n\t    </div>\n    "
        }), 
        __metadata('design:paramtypes', [platform_browser_1.DomSanitizer])
    ], DocumentDefinitionComponent);
    return DocumentDefinitionComponent;
}());
exports.DocumentDefinitionComponent = DocumentDefinitionComponent;
//# sourceMappingURL=document.definition.component.js.map
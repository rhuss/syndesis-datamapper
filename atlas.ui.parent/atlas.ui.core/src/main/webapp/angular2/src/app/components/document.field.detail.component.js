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
var platform_browser_1 = require('@angular/platform-browser');
var config_model_1 = require('../models/config.model');
var field_model_1 = require('../models/field.model');
var document_definition_model_1 = require('../models/document.definition.model');
var line_machine_component_1 = require('./line.machine.component');
var DocumentFieldDetailComponent = (function () {
    function DocumentFieldDetailComponent(sanitizer) {
        this.sanitizer = sanitizer;
        this.collapsed = true;
        this.parentToggleClass = "arrow fa fa-angle-right";
    }
    DocumentFieldDetailComponent.prototype.getCssClass = function () {
        var cssClass = "fieldDetail";
        if (this.field.selected) {
            cssClass += " selectedField";
        }
        if (!this.field.isTerminal()) {
            cssClass += " parentField";
        }
        if (!this.docDef.isInput) {
            cssClass += " outputField";
        }
        return cssClass;
    };
    DocumentFieldDetailComponent.prototype.getElementPosition = function () {
        var x = 0;
        var y = 0;
        var el = this.fieldDetailElement.nativeElement;
        while (el != null) {
            x += el.offsetLeft;
            y += el.offsetTop;
            el = el.offsetParent;
        }
        return { "x": x, "y": y };
    };
    DocumentFieldDetailComponent.prototype.handleMouseOver = function (event) {
        if (this.field.isTerminal()) {
            this.lineMachine.handleDocumentFieldMouseOver(this, event);
        }
    };
    DocumentFieldDetailComponent.prototype.handleMouseClick = function (event) {
        if (this.field.isTerminal()) {
            this.field.selected = !this.field.selected;
            this.cfg.documentService.notifyUpdateFromSelectedFields();
        }
        else {
            this.collapsed = !this.collapsed;
            this.parentToggleClass = "arrow fa fa-angle-" + (this.collapsed ? "right" : "down");
        }
    };
    DocumentFieldDetailComponent.prototype.getFieldDetailComponent = function (fieldPath) {
        if (this.field.path == fieldPath) {
            return this;
        }
        for (var _i = 0, _a = this.fieldComponents.toArray(); _i < _a.length; _i++) {
            var c = _a[_i];
            var returnedComponent = c.getFieldDetailComponent(fieldPath);
            if (returnedComponent != null) {
                return returnedComponent;
            }
        }
        return null;
    };
    DocumentFieldDetailComponent.prototype.getSpacerWidth = function () {
        var width = (this.field.fieldDepth * 30).toString();
        return this.sanitizer.bypassSecurityTrustStyle("display:inline; margin-left:" + width + "px");
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], DocumentFieldDetailComponent.prototype, "cfg", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', document_definition_model_1.DocumentDefinition)
    ], DocumentFieldDetailComponent.prototype, "docDef", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', field_model_1.Field)
    ], DocumentFieldDetailComponent.prototype, "field", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', line_machine_component_1.LineMachineComponent)
    ], DocumentFieldDetailComponent.prototype, "lineMachine", void 0);
    __decorate([
        core_1.ViewChild('fieldDetailElement'), 
        __metadata('design:type', core_1.ElementRef)
    ], DocumentFieldDetailComponent.prototype, "fieldDetailElement", void 0);
    __decorate([
        core_1.ViewChildren('fieldDetail'), 
        __metadata('design:type', core_1.QueryList)
    ], DocumentFieldDetailComponent.prototype, "fieldComponents", void 0);
    DocumentFieldDetailComponent = __decorate([
        core_1.Component({
            selector: 'document-field-detail',
            template: "\n\t\t<div class=\"DocumentFieldDetailComponent\" #fieldDetailElement on-mouseover='handleMouseOver($event)'>\n\t\t\t<div [attr.class]='getCssClass()' \n\t\t\t\t(click)=\"handleMouseClick($event)\" *ngIf=\"field.visible\">\t\t\t\t\n\t\t\t\t<div style=\"float:left;\">\n\t\t\t\t\t<div style=\"width:10px; text-align:center; display:inline-block;\" *ngIf=\"!docDef.isInput && field.isTerminal()\">\n\t\t\t\t\t\t<i class=\"partOfMappingIconLeft fa fa-circle\" \n\t\t\t\t\t\t\t*ngIf=\"!docDef.isInput && field.isTerminal() && field.partOfMapping\"></i>\n\t\t\t\t\t</div>\n\t\t\t\t\t<div class=\"spacer\" [attr.style]=\"getSpacerWidth()\">&nbsp;</div>\n\t\t\t\t\t<i *ngIf=\"!field.isTerminal()\" [attr.class]=\"parentToggleClass\"></i>\n\t\t  \t\t\t<label>{{field.displayName}}</label>\n\t\t  \t\t</div>\n\t\t  \t\t<div style=\"float:right;\">\n\t\t  \t\t\t<i class=\"partOfMappingIconRight fa fa-circle\" \n\t\t  \t\t\t\t*ngIf=\"(docDef.isInput || !field.isTerminal()) && field.partOfMapping\"></i>\n\t\t  \t\t</div>\n\t\t  \t\t<div style=\"clear:both; height:0px;\">&nbsp;</div>\n\t\t  \t</div>\n\t\t  \t<div class=\"childrenFields\" *ngIf=\"!field.isTerminal() && !collapsed\">\n\t\t  \t\t<document-field-detail #fieldDetail *ngFor=\"let f of field.children\" \n\t\t\t\t\t[field]=\"f\" [docDef]=\"docDef\" [lineMachine]=\"lineMachine\" \n\t\t\t\t\t[cfg]=\"cfg\"></document-field-detail>\n\t\t\t</div>\n\t\t</div>\t  \t\n    "
        }), 
        __metadata('design:paramtypes', [platform_browser_1.DomSanitizer])
    ], DocumentFieldDetailComponent);
    return DocumentFieldDetailComponent;
}());
exports.DocumentFieldDetailComponent = DocumentFieldDetailComponent;
//# sourceMappingURL=document.field.detail.component.js.map
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
var field_model_1 = require('../models/field.model');
var mapping_model_1 = require('../models/mapping.model');
var MappingFieldActionComponent = (function () {
    function MappingFieldActionComponent() {
        this.isInput = false;
    }
    __decorate([
        core_1.Input(), 
        __metadata('design:type', field_model_1.Field)
    ], MappingFieldActionComponent.prototype, "field", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', mapping_model_1.MappingModel)
    ], MappingFieldActionComponent.prototype, "mapping", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Boolean)
    ], MappingFieldActionComponent.prototype, "isInput", void 0);
    MappingFieldActionComponent = __decorate([
        core_1.Component({
            selector: 'mapping-field-action',
            template: "\n\t\t<div *ngIf=\"isInput == false && mapping.transition.isSeparateMode()\" class=\"form-group\" style=\"margin-right:22px;\">\n\t\t\t<div style=\"float:right\">\n\t\t\t\t<label style=\"width:50px;\">Index:</label>\n\t\t\t\t<input type=\"text\" [(ngModel)]=\"mapping.fieldSeparatorIndexes[field.path]\" \n\t\t\t\t\tstyle=\"width:50px; text-align:right;\"/>\n\t\t\t</div>\n\t\t\t<div style=\"clear:both; height:0px;\"></div>\n\t\t</div>\n\t"
        }), 
        __metadata('design:paramtypes', [])
    ], MappingFieldActionComponent);
    return MappingFieldActionComponent;
}());
exports.MappingFieldActionComponent = MappingFieldActionComponent;
//# sourceMappingURL=mapping.field.action.component.js.map
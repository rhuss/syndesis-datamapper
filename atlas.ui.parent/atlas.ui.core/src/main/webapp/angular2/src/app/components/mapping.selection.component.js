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
var mapping_model_1 = require('../models/mapping.model');
var MappingSelectionComponent = (function () {
    function MappingSelectionComponent() {
    }
    MappingSelectionComponent.prototype.selectionChanged = function (event) {
        var eventTarget = event.target;
        for (var _i = 0, _a = this.mappings; _i < _a.length; _i++) {
            var m = _a[_i];
            if (m.uuid == eventTarget.value) {
                this.selectedMapping = m;
                break;
            }
        }
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array)
    ], MappingSelectionComponent.prototype, "mappings", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', mapping_model_1.MappingModel)
    ], MappingSelectionComponent.prototype, "selectedMapping", void 0);
    MappingSelectionComponent = __decorate([
        core_1.Component({
            selector: 'mapping-selection',
            template: "\n\t\t<div class=\"MappingSelectionComponent\" *ngIf=\"selectedMapping\">\n\t\t\t<table>\n\t\t\t\t<tr class=\"headers\">\n\t\t\t\t\t<th class=\"radios\"></th>\n\t\t\t\t\t<th class=\"inputFields\">Source</th>\n\t\t\t\t\t<th class=\"outputFields\">Target</th>\n\t\t\t\t</tr>\n\t\t\t\t<tr *ngFor=\"let m of mappings; let i = index; let odd=odd; let even=even;\" \n\t\t\t\t\t[class]=\"odd ? 'odd' : 'even'\">\t\t\t\t\n\t\t\t\t\t<td class=\"radios\">\n\t\t\t\t\t\t<input name=\"options\" type=\"radio\" \n\t\t\t\t\t\t\t[attr.value]=\"m.uuid\"\n\t\t\t\t\t\t\t[attr.checked]=\"selectedMapping.uuid == m.uuid ? 'checked' : null\"\n\t\t\t\t\t\t\t(click)=\"selectionChanged($event)\" /><br/>\n\t\t\t\t\t</td>\n\t\t\t\t\t<td class=\"inputFields\">\n\t\t\t\t\t\t<div class=\"inputField\" *ngFor=\"let field of m.inputFieldPaths\">{{field}}</div>\n\t\t\t\t\t</td>\n\t\t\t\t\t<td class=\"outputFields\">\n\t\t\t\t\t\t<div class=\"outputField\" *ngFor=\"let field of m.outputFieldPaths\">{{field}}</div>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\t\n\t\t\t</table>\t\t\n\t\t</div>\n\t"
        }), 
        __metadata('design:paramtypes', [])
    ], MappingSelectionComponent);
    return MappingSelectionComponent;
}());
exports.MappingSelectionComponent = MappingSelectionComponent;
//# sourceMappingURL=mapping.selection.component.js.map
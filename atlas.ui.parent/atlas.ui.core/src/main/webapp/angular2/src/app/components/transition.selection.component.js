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
var transition_model_1 = require('../models/transition.model');
var TransitionSelectionComponent = (function () {
    function TransitionSelectionComponent() {
        this.model = new transition_model_1.TransitionModel();
        this.modes = transition_model_1.TransitionMode;
        this.delimeters = transition_model_1.TransitionDelimiter;
    }
    TransitionSelectionComponent.prototype.selectionChanged = function (event) {
        var eventTarget = event.target; //extract this to avoid compiler error about 'selectedOptions' not existing.	
        var selectorIsMode = "mode" == eventTarget.attributes.getNamedItem("selector").value;
        var selectedValue = eventTarget.selectedOptions.item(0).attributes.getNamedItem("value").value;
        if (selectorIsMode) {
            this.model.mode = parseInt(selectedValue);
        }
        else {
            this.model.delimiter = parseInt(selectedValue);
        }
    };
    TransitionSelectionComponent = __decorate([
        core_1.Component({
            selector: 'transition-selector',
            template: "\n\t\t<div class=\"TransitionSelector\" *ngIf=\"model\">\n\t\t\t<div class=\"form-group\">\n\t\t\t\t<label>Action:</label>\n\t\t\t\t<select (change)=\"selectionChanged($event);\" selector=\"mode\" [ngModel]=\"model.mode\">\n\t\t\t\t\t<option value=\"{{modes.MAP}}\">Map</option>\n\t\t\t\t\t<option value=\"{{modes.SEPARATE}}\">Separate</option>\n\t\t\t\t</select>\n\t\t\t</div>\n\t\t\t<div class=\"form-group\" *ngIf=\"model.mode == modes.SEPARATE\">\n\t\t\t\t<label>Separator:</label>\n\t\t\t\t<select (change)=\"selectionChanged($event);\" selector=\"separator\" [ngModel]=\"model.delimiter\">\n\t\t\t\t\t<option value=\"{{delimeters.SPACE}}\">Space</option>\n\t\t\t\t\t<option value=\"{{delimeters.COMMA}}\">Comma</option>\n\t\t\t\t</select>\n\t\t\t</div>\n\t\t</div>\n\t"
        }), 
        __metadata('design:paramtypes', [])
    ], TransitionSelectionComponent);
    return TransitionSelectionComponent;
}());
exports.TransitionSelectionComponent = TransitionSelectionComponent;
//# sourceMappingURL=transition.selection.component.js.map
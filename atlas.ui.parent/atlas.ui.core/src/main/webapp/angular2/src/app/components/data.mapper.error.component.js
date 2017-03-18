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
var error_handler_service_1 = require('../services/error.handler.service');
var DataMapperErrorComponent = (function () {
    function DataMapperErrorComponent() {
    }
    DataMapperErrorComponent.prototype.handleClick = function (event) {
        // need to extract this so typescript doesnt throw compiler error
        var eventTarget = event.target;
        var errorIdentifier = eventTarget.attributes.getNamedItem("errorIdentifier").value;
        this.errorService.removeError(errorIdentifier);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', error_handler_service_1.ErrorHandlerService)
    ], DataMapperErrorComponent.prototype, "errorService", void 0);
    DataMapperErrorComponent = __decorate([
        core_1.Component({
            selector: 'data-mapper-error',
            template: "\n\t\t<div class=\"DataMapperErrorComponent\" *ngIf=\"errorService && errorService.errors.length\">\n\t\t\t<div class=\"alert alert-danger\" *ngFor=\"let e of errorService.errors\">\n\t\t\t\t<a class=\"close\" (click)=\"handleClick($event)\"><i class=\"fa fa-close\" attr.errorIdentifier=\"{{e.identifier}}\"></i></a>\n\t            <span class=\"pficon pficon-error-circle-o\"></span>{{e.message}}\n\t        </div>\n        </div>\n\t"
        }), 
        __metadata('design:paramtypes', [])
    ], DataMapperErrorComponent);
    return DataMapperErrorComponent;
}());
exports.DataMapperErrorComponent = DataMapperErrorComponent;
//# sourceMappingURL=data.mapper.error.component.js.map
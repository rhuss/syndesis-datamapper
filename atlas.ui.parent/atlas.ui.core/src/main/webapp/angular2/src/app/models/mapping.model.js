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
var transition_model_1 = require('./transition.model');
var MappingModel = (function () {
    function MappingModel() {
        this.inputFieldPaths = [];
        this.outputFieldPaths = [];
        this.saved = false;
        this.transition = new transition_model_1.TransitionModel();
        this.fieldSeparatorIndexes = {};
    }
    MappingModel.prototype.updateSeparatorIndexes = function () {
        var isSeparateMapping = (this.transition.mode == transition_model_1.TransitionMode.SEPARATE);
        for (var _i = 0, _a = this.inputFieldPaths.concat(this.outputFieldPaths); _i < _a.length; _i++) {
            var fieldPath = _a[_i];
            if (this.fieldSeparatorIndexes[fieldPath] == null) {
                this.fieldSeparatorIndexes[fieldPath] = "1";
            }
        }
    };
    return MappingModel;
}());
exports.MappingModel = MappingModel;
//# sourceMappingURL=mapping.model.js.map
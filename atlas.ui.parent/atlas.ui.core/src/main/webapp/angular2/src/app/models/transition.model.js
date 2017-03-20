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
(function (TransitionMode) {
    TransitionMode[TransitionMode["MAP"] = 0] = "MAP";
    TransitionMode[TransitionMode["SEPARATE"] = 1] = "SEPARATE";
})(exports.TransitionMode || (exports.TransitionMode = {}));
var TransitionMode = exports.TransitionMode;
(function (TransitionDelimiter) {
    TransitionDelimiter[TransitionDelimiter["SPACE"] = 0] = "SPACE";
    TransitionDelimiter[TransitionDelimiter["COMMA"] = 1] = "COMMA";
})(exports.TransitionDelimiter || (exports.TransitionDelimiter = {}));
var TransitionDelimiter = exports.TransitionDelimiter;
var TransitionModel = (function () {
    function TransitionModel() {
        this.mode = TransitionMode.MAP;
        this.delimiter = TransitionDelimiter.SPACE;
    }
    TransitionModel.prototype.getPrettyName = function () {
        if (this.mode == TransitionMode.SEPARATE) {
            return "Separate (" + ((this.delimiter == TransitionDelimiter.SPACE) ? "Space)" : "Comma)");
        }
        return "Map";
    };
    TransitionModel.prototype.isSeparateMode = function () {
        return this.mode == TransitionMode.SEPARATE;
    };
    return TransitionModel;
}());
exports.TransitionModel = TransitionModel;
//# sourceMappingURL=transition.model.js.map
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
(function (ErrorLevel) {
    ErrorLevel[ErrorLevel["DEBUG"] = 0] = "DEBUG";
    ErrorLevel[ErrorLevel["INFO"] = 1] = "INFO";
    ErrorLevel[ErrorLevel["WARN"] = 2] = "WARN";
    ErrorLevel[ErrorLevel["ERROR"] = 3] = "ERROR";
})(exports.ErrorLevel || (exports.ErrorLevel = {}));
var ErrorLevel = exports.ErrorLevel;
var ErrorInfo = (function () {
    function ErrorInfo() {
    }
    return ErrorInfo;
}());
exports.ErrorInfo = ErrorInfo;
//# sourceMappingURL=error.model.js.map
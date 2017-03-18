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
var Field = (function () {
    function Field() {
        this.partOfMapping = false;
        this.visible = true;
        this.selected = false;
        this.children = [];
        this.fieldDepth = 0;
    }
    Field.prototype.isTerminal = function () {
        return (this.children == null || this.children.length == 0);
    };
    Field.prototype.copy = function () {
        var copy = new Field();
        copy.name = this.name;
        copy.displayName = this.displayName;
        copy.path = this.path;
        copy.type = this.type;
        copy.serviceObject = this.serviceObject;
        copy.parentField = null;
        copy.partOfMapping = this.partOfMapping;
        copy.visible = this.visible;
        copy.selected = this.selected;
        for (var _i = 0, _a = this.children; _i < _a.length; _i++) {
            var childField = _a[_i];
            copy.children.push(childField.copy());
        }
        return copy;
    };
    return Field;
}());
exports.Field = Field;
//# sourceMappingURL=field.model.js.map
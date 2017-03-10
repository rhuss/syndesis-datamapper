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
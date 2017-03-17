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
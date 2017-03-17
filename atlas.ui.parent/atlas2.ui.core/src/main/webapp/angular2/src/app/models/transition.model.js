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
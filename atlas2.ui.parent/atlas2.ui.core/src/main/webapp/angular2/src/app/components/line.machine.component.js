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
var platform_browser_1 = require('@angular/platform-browser');
var config_model_1 = require('../models/config.model');
var document_definition_component_1 = require('./document.definition.component');
var LineModel = (function () {
    function LineModel() {
        this.color = "blue";
        this.strokeWidth = "1";
    }
    return LineModel;
}());
exports.LineModel = LineModel;
var LineMachineComponent = (function () {
    function LineMachineComponent(sanitizer) {
        this.sanitizer = sanitizer;
        this.lines = [];
        this.drawingLine = false;
    }
    LineMachineComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.cfg.mappingService.activeMappingChanged$.subscribe(function (mappingIsNew) {
            _this.activeMappingChanged(mappingIsNew);
        });
        this.cfg.mappingService.mappingUpdated$.subscribe(function () {
            _this.activeMappingChanged(false);
        });
    };
    LineMachineComponent.prototype.addLineFromParams = function (sourceX, sourceY, targetX, targetY, color, strokeWidth) {
        var l = new LineModel();
        l.sourceX = sourceX;
        l.sourceY = sourceY;
        l.targetX = targetX;
        l.targetY = targetY;
        l.color = color;
        l.strokeWidth = strokeWidth;
        this.addLine(l);
    };
    LineMachineComponent.prototype.addLine = function (l) {
        this.createLineStyle(l);
        this.lines.push(l);
    };
    LineMachineComponent.prototype.createLineStyle = function (l) {
        //angular2 will throw an error if we don't use this sanitizer to signal to angular2 that the css style value is ok.
        l.style = this.sanitizer.bypassSecurityTrustStyle("stroke:" + l.color + "; stroke-width:" + l.strokeWidth + ";");
    };
    LineMachineComponent.prototype.setLineBeingFormed = function (l) {
        if (l != null) {
            this.createLineStyle(l);
        }
        this.lineBeingFormed = l;
    };
    LineMachineComponent.prototype.clearLines = function () {
        this.lines = [];
    };
    LineMachineComponent.prototype.drawLine = function (event) {
        this.drawCurrentLine(event.offsetX.toString(), event.offsetY.toString());
    };
    LineMachineComponent.prototype.drawCurrentLine = function (x, y) {
        if (this.drawingLine && this.lineBeingFormed) {
            this.lineBeingFormed.targetX = x;
            this.lineBeingFormed.targetY = y;
        }
    };
    LineMachineComponent.prototype.handleDocumentFieldMouseOver = function (component, event) {
        if (!this.drawingLine) {
            return;
        }
        var isOutput = (component.docDef.isInput == false);
        if (!isOutput) {
            return;
        }
        var targetY = this.docDefOutput.getFieldDetailComponentPosition(component.field.path).y;
        this.drawCurrentLine("100%", (targetY + 17).toString());
    };
    LineMachineComponent.prototype.activeMappingChanged = function (mappingIsNew) {
        var _this = this;
        if (!mappingIsNew) {
            this.drawingLine = false;
            this.setLineBeingFormed(null);
        }
        else {
            var isInput = (this.cfg.mappings.activeMapping.inputFieldPaths.length == 1);
            if (isInput) {
                var fieldPathToFind = this.cfg.mappings.activeMapping.inputFieldPaths[0];
                var sourceY = this.docDefInput.getFieldDetailComponentPosition(fieldPathToFind).y;
                var l = new LineModel();
                l.sourceX = "0";
                l.sourceY = (sourceY + 17).toString();
                l.color = "#02A2D7";
                l.strokeWidth = "3";
                this.setLineBeingFormed(l);
                this.drawingLine = true;
            }
        }
        // update the mapping line drawing after our fields have redrawn themselves
        // without this, the x/y from the field dom elements is messed up / misaligned.
        setTimeout(function () { _this.redrawLinesForMappings(); }, 1);
    };
    LineMachineComponent.prototype.updateHeight = function () {
        if (this.cfg.inputDoc && this.cfg.outputDoc) {
            var maxFieldCount = Math.max(this.cfg.inputDoc.fields.length, this.cfg.outputDoc.fields.length);
            var heightCSS = ((maxFieldCount * 40) + 100).toString() + "px";
            this.svgStyle = this.sanitizer.bypassSecurityTrustStyle("width:100%; height:" + heightCSS + ";");
        }
    };
    LineMachineComponent.prototype.redrawLinesForMappings = function () {
        if (!this.cfg.mappings.activeMapping) {
            this.setLineBeingFormed(null);
        }
        this.clearLines();
        var mappings = this.cfg.mappings.mappings;
        var activeMapping = this.cfg.mappings.activeMapping;
        var foundSelectedMapping = false;
        for (var _i = 0, mappings_1 = mappings; _i < mappings_1.length; _i++) {
            var m = mappings_1[_i];
            foundSelectedMapping = foundSelectedMapping || (m == activeMapping);
            this.drawLinesForMapping(m);
        }
        if (!foundSelectedMapping && activeMapping) {
            this.drawLinesForMapping(activeMapping);
        }
    };
    LineMachineComponent.prototype.drawLinesForMapping = function (m) {
        if (m.inputFieldPaths.length && m.outputFieldPaths.length) {
            //don't draw mapping for this mapping if any of the fields aren't visible
            for (var _i = 0, _a = m.inputFieldPaths; _i < _a.length; _i++) {
                var inputFieldPath = _a[_i];
                var inputField = this.cfg.inputDoc.getField(inputFieldPath);
                if (!inputField) {
                    console.error("Can't find input field, not drawing line: " + inputFieldPath);
                    return;
                }
                if (!inputField.visible) {
                    return;
                }
            }
            for (var _b = 0, _c = m.outputFieldPaths; _b < _c.length; _b++) {
                var outputFieldPath = _c[_b];
                var outputField = this.cfg.outputDoc.getField(outputFieldPath);
                if (!outputField) {
                    console.error("Can't find output field, not drawing line: " + outputFieldPath);
                    return;
                }
                if (!outputField.visible) {
                    return;
                }
            }
            //draw lines for the given mapping
            for (var _d = 0, _e = m.inputFieldPaths; _d < _e.length; _d++) {
                var inputFieldPath = _e[_d];
                for (var _f = 0, _g = m.outputFieldPaths; _f < _g.length; _f++) {
                    var outputFieldPath = _g[_f];
                    var sourceY = this.docDefInput.getFieldDetailComponentPosition(inputFieldPath).y;
                    var targetY = this.docDefOutput.getFieldDetailComponentPosition(outputFieldPath).y;
                    var isSelectedMapping = (this.cfg.mappings.activeMapping == m);
                    var color = isSelectedMapping ? "#02A2D7" : "#A2A2A2";
                    var strokeWidth = isSelectedMapping ? "3" : "1";
                    this.addLineFromParams("0", (sourceY + 17).toString(), "100%", (targetY + 17).toString(), color, strokeWidth);
                }
            }
        }
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], LineMachineComponent.prototype, "cfg", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', document_definition_component_1.DocumentDefinitionComponent)
    ], LineMachineComponent.prototype, "docDefInput", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', document_definition_component_1.DocumentDefinitionComponent)
    ], LineMachineComponent.prototype, "docDefOutput", void 0);
    LineMachineComponent = __decorate([
        core_1.Component({
            selector: 'line-machine',
            template: "\n\t\t<div class=\"LineMachineComponent\" on-mousemove=\"drawLine($event)\">\n\t\t\t<svg [attr.style]=\"svgStyle\">\n\t\t\t\t<svg:line *ngFor=\"let l of lines\" \n\t\t\t\t\t[attr.x1]=\"l.sourceX\" [attr.y1]=\"l.sourceY\" \n\t\t\t\t\t[attr.x2]=\"l.targetX\" [attr.y2]=\"l.targetY\" \n\t\t\t\t\tshape-rendering=\"optimizeQuality\"\n\t\t\t\t\t[attr.style]=\"l.style\"></svg:line>\n\t\t\t\t<svg:line *ngIf=\"lineBeingFormed && lineBeingFormed.targetY\" \n\t\t\t\t\t[attr.x1]=\"lineBeingFormed.sourceX\" [attr.y1]=\"lineBeingFormed.sourceY\" \n\t\t\t\t\t[attr.x2]=\"lineBeingFormed.targetX\" [attr.y2]=\"lineBeingFormed.targetY\" \n\t\t\t\t\tshape-rendering=\"optimizeQuality\"\n\t\t\t\t\t[attr.style]=\"lineBeingFormed.style\"></svg:line>\n\t\t\t</svg>\n\t\t</div>\n\t"
        }), 
        __metadata('design:paramtypes', [platform_browser_1.DomSanitizer])
    ], LineMachineComponent);
    return LineMachineComponent;
}());
exports.LineMachineComponent = LineMachineComponent;
//# sourceMappingURL=line.machine.component.js.map
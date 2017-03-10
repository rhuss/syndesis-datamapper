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
var config_model_1 = require('../models/config.model');
var ToolbarComponent = (function () {
    function ToolbarComponent() {
    }
    ToolbarComponent.prototype.buttonClicked = function (action) {
        this.buttonClickedHandler(action, this);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Function)
    ], ToolbarComponent.prototype, "buttonClickedHandler", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', core_1.Component)
    ], ToolbarComponent.prototype, "parentComponent", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', config_model_1.ConfigModel)
    ], ToolbarComponent.prototype, "cfg", void 0);
    ToolbarComponent = __decorate([
        core_1.Component({
            selector: 'toolbar',
            template: "\n    <div class=\"row toolbar-pf\" style=\"padding-top:0px;\">\n      <div class=\"col-sm-12\">\n          <!--  \n          <div class=\"form-group\" style=\"padding-left:0px; padding-bottom:10px;\">\n            <button class=\"btn btn-default\" type=\"button\" (click)=\"buttonClicked('add');\"><i class=\"fa fa-plus\"></i> Add Mapping</button>\n          </div>    \n                \n          <div class=\"form-group toolbar-pf-filter\" style=\"border-right:0px solid black; padding-left:20px;\">\n            <label class=\"sr-only\" for=\"filter\">Name</label>\n            <div class=\"input-group\">\n              <div class=\"input-group-btn\">\n                <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Name <span class=\"caret\"></span></button>\n                <ul class=\"dropdown-menu\">\n                  <li class=\"selected\"><a href=\"#\">Name</a></li>\n                  <li><a href=\"#\">Type</a></li>\n                  <li><a href=\"#\">Color</a></li>\n                </ul>\n              </div>\n              <input type=\"text\" class=\"form-control\" id=\"filter\" placeholder=\"Filter By Name...\">\n            </div>\n          </div>\n          -->\n          <div class=\"toolbar-pf-action-right\">\n            <!--\n            <div class=\"form-group\">\n              <label>Alerts</label><div style=\"display:inline; margin-left:10px; margin-right:0px; color:white; background-color:#888888; padding:1px 10px;\">3</div>\n            </div>\n            -->\n            <div class=\"form-group toolbar-pf-view-selector\">\n              <!--\n              <button class=\"btn btn-link \" style=\"margin-top:-8px;\" \n                (click)=\"buttonClicked('add');\"><i class=\"fa fa-plus\"></i></button>\n              -->\n              <button class=\"btn btn-link \" style=\"margin-top:-8px;\" *ngIf=\"!cfg.showMappingDetailTray\"\n                (click)=\"buttonClicked('showDetails');\"><i class=\"fa fa-exchange\"></i></button>\n              <button class=\"btn btn-link \" style=\"margin-top:-8px; color:#0088ce;\" *ngIf=\"cfg.showMappingDetailTray\"\n                (click)=\"buttonClicked('showDetails');\"><i class=\"fa fa-exchange\"></i></button>\n            </div>\n          </div> \n      </div>\n    </div>\n  "
        }), 
        __metadata('design:paramtypes', [])
    ], ToolbarComponent);
    return ToolbarComponent;
}());
exports.ToolbarComponent = ToolbarComponent;
//# sourceMappingURL=toolbar.component.js.map
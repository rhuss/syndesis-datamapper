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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
var core_1 = require('@angular/core');
var platform_browser_1 = require('@angular/platform-browser');
// source: http://www.w3schools.com/howto/howto_css_modals.asp
var ModalWindowComponent = (function () {
    function ModalWindowComponent(componentFactoryResolver, document, sanitizer) {
        this.componentFactoryResolver = componentFactoryResolver;
        this.document = document;
        this.sanitizer = sanitizer;
        this.headerText = "";
        this.componentLoaded = false;
        this.visible = false;
    }
    ModalWindowComponent.prototype.ngAfterViewInit = function () {
        var _this = this;
        //from: http://stackoverflow.com/questions/40811809/add-component-dynamically-inside-an-ngif
        this.myTarget.changes.subscribe(function (changes) {
            if (!_this.componentLoaded && _this.visible && _this.myTarget && (_this.myTarget.toArray().length)) {
                //break us out of a change detection call stack with timeout so we can change data on created comp.
                setTimeout(function () { _this.loadComponent(); }, 1);
            }
        });
    };
    ModalWindowComponent.prototype.loadComponent = function () {
        var viewContainerRef = this.myTarget.toArray()[0];
        viewContainerRef.clear();
        var componentFactory = this.componentFactoryResolver.resolveComponentFactory(this.nestedComponentType);
        this.nestedComponent = viewContainerRef.createComponent(componentFactory).instance;
        this.nestedComponentInitializedCallback(this);
    };
    ModalWindowComponent.prototype.closeClicked = function (event) { this.buttonClicked(false); };
    ModalWindowComponent.prototype.close = function () { this.visible = false; };
    ModalWindowComponent.prototype.show = function () {
        var number = (this.document.body.scrollTop);
        this.modalStyle = this.sanitizer.bypassSecurityTrustStyle("top:" + number + "px;");
        this.visible = true;
    };
    ModalWindowComponent.prototype.buttonClicked = function (okClicked) {
        if (okClicked) {
            if (this.okButtonHandler) {
                this.okButtonHandler(this);
            }
        }
        else {
            if (this.cancelButtonHandler) {
                this.cancelButtonHandler(this);
            }
        }
        this.close();
    };
    ModalWindowComponent.prototype.reset = function () {
        this.headerText = "";
        this.parentComponent = null;
        this.componentLoaded = false;
        this.nestedComponentType = null;
        this.okButtonHandler = null;
        this.cancelButtonHandler = null;
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], ModalWindowComponent.prototype, "headerText", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', core_1.Component)
    ], ModalWindowComponent.prototype, "parentComponent", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', core_1.Type)
    ], ModalWindowComponent.prototype, "nestedComponentType", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Function)
    ], ModalWindowComponent.prototype, "nestedComponentInitializedCallback", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Function)
    ], ModalWindowComponent.prototype, "okButtonHandler", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Function)
    ], ModalWindowComponent.prototype, "cancelButtonHandler", void 0);
    __decorate([
        core_1.ViewChildren('dyn_target', { read: core_1.ViewContainerRef }), 
        __metadata('design:type', core_1.QueryList)
    ], ModalWindowComponent.prototype, "myTarget", void 0);
    ModalWindowComponent = __decorate([
        core_1.Component({
            selector: 'modal-window',
            template: "\n\t\t<div id=\"modalWindow\" class=\"modalWindow\" *ngIf=\"visible\" [attr.style]=\"modalStyle\">\n\t\t\t<div class=\"modal-content\">\n\t\t\t\t<div class=\"modal-header\">\n\t\t\t\t\t<a (click)=\"closeClicked($event)\"><span class='close'><i class=\"fa fa-close\"></i></span></a>\n\t\t\t\t\t{{headerText}}\n\t\t\t\t</div>\n\t\t\t\t<div class=\"modal-body\">\n\t\t\t\t\t<template #dyn_target></template>\n\t\t\t\t\t<div class=\"modal-buttons\">\t\t\t\t\t\n\t\t\t\t\t\t<a class='button' (click)=\"buttonClicked(false)\"><i class=\"fa fa-close\"></i></a>\t  \t\t\n\t\t\t\t\t\t<a class='button' (click)=\"buttonClicked(true)\"><i class=\"fa fa-check\"></i></a>\t  \t\t\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"modal-footer\"></div>\n\t\t\t</div>\n\t\t</div>\n    "
        }),
        __param(1, core_1.Inject(platform_browser_1.DOCUMENT)), 
        __metadata('design:paramtypes', [core_1.ComponentFactoryResolver, Document, platform_browser_1.DomSanitizer])
    ], ModalWindowComponent);
    return ModalWindowComponent;
}());
exports.ModalWindowComponent = ModalWindowComponent;
//# sourceMappingURL=modal.window.component.js.map
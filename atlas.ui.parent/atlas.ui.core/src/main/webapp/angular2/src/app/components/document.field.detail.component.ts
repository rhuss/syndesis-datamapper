///
/// Copyright (C) 2017 Red Hat, Inc.
///
/// Licensed under the Apache License, Version 2.0 (the "License");
/// you may not use this file except in compliance with the License.
/// You may obtain a copy of the License at
///
///         http://www.apache.org/licenses/LICENSE-2.0
///
/// Unless required by applicable law or agreed to in writing, software
/// distributed under the License is distributed on an "AS IS" BASIS,
/// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
/// See the License for the specific language governing permissions and
/// limitations under the License.
///

import { Component, Input, ElementRef, ViewChild, ViewChildren, QueryList } from '@angular/core';
import { DomSanitizer, SafeResourceUrl, SafeUrl, SafeStyle} from '@angular/platform-browser';

import { ConfigModel } from '../models/config.model';
import { Field } from '../models/field.model';   
import { DocumentDefinition } from '../models/document.definition.model';

import { DocumentManagementService } from '../services/document.management.service';

import { LineMachineComponent } from './line.machine.component';

@Component({
	selector: 'document-field-detail',
	template: `
		<div class="DocumentFieldDetailComponent" #fieldDetailElement on-mouseover='handleMouseOver($event)'>
			<div [attr.class]='getCssClass()' 
				(click)="handleMouseClick($event)" *ngIf="field.visible">				
				<div style="float:left;">
					<div style="width:10px; text-align:center; display:inline-block;" *ngIf="!docDef.isInput && field.isTerminal()">
						<i class="partOfMappingIconLeft fa fa-circle" 
							*ngIf="!docDef.isInput && field.isTerminal() && field.partOfMapping"></i>
					</div>
					<div class="spacer" [attr.style]="getSpacerWidth()">&nbsp;</div>
					<i *ngIf="!field.isTerminal()" [attr.class]="parentToggleClass"></i>
		  			<label>{{field.displayName}}</label>
		  		</div>
		  		<div style="float:right;">
		  			<i class="partOfMappingIconRight fa fa-circle" 
		  				*ngIf="(docDef.isInput || !field.isTerminal()) && field.partOfMapping"></i>
		  		</div>
		  		<div style="clear:both; height:0px;">&nbsp;</div>
		  	</div>
		  	<div class="childrenFields" *ngIf="!field.isTerminal() && !collapsed">
		  		<document-field-detail #fieldDetail *ngFor="let f of field.children" 
					[field]="f" [docDef]="docDef" [lineMachine]="lineMachine" 
					[cfg]="cfg"></document-field-detail>
			</div>
		</div>	  	
    `
})

export class DocumentFieldDetailComponent { 
	@Input() cfg: ConfigModel;
	@Input() docDef: DocumentDefinition;
	@Input() field: Field;	
	@Input() lineMachine: LineMachineComponent;

	@ViewChild('fieldDetailElement') fieldDetailElement:ElementRef;
	@ViewChildren('fieldDetail') fieldComponents: QueryList<DocumentFieldDetailComponent>;

	private collapsed: boolean = true;
	private parentToggleClass: string = "arrow fa fa-angle-right";

	constructor(private sanitizer: DomSanitizer) {}

	private getCssClass(): string {
		var cssClass: string = "fieldDetail";
		if (this.field.selected) {
			cssClass += " selectedField";
		}
		if (!this.field.isTerminal()) {
			cssClass += " parentField";
		}
		if (!this.docDef.isInput) {
			cssClass += " outputField";
		}
		return cssClass;
	}

	public getElementPosition(): any {
		var x: number = 0;
		var y: number = 0;
		
		var el: any = this.fieldDetailElement.nativeElement;
		while (el != null) {
			x += el.offsetLeft;
			y += el.offsetTop;
			el = el.offsetParent;
		}
		return { "x": x, "y":y };
	}

	handleMouseOver(event: MouseEvent): void {
		if (this.field.isTerminal()) {
			this.lineMachine.handleDocumentFieldMouseOver(this, event);
		}
	}

	handleMouseClick(event: MouseEvent): void {
		if (this.field.isTerminal()) {
			this.field.selected = !this.field.selected;		
			this.cfg.documentService.notifyUpdateFromSelectedFields();
		} else { //parent field
			this.collapsed = !this.collapsed;
			this.parentToggleClass = "arrow fa fa-angle-" + (this.collapsed ? "right" : "down");
		}		
	}	

	public getFieldDetailComponent(fieldPath: string): DocumentFieldDetailComponent {
		if (this.field.path == fieldPath) {
			return this;
		}
        for (let c of this.fieldComponents.toArray()) {
        	var returnedComponent: DocumentFieldDetailComponent = c.getFieldDetailComponent(fieldPath);
        	if (returnedComponent != null) {
        		return returnedComponent;
        	}
        }
        return null;
    }  

    private getSpacerWidth(): SafeStyle {
    	var width: string = (this.field.fieldDepth * 30).toString();
    	return this.sanitizer.bypassSecurityTrustStyle("display:inline; margin-left:" + width + "px");
    }
}
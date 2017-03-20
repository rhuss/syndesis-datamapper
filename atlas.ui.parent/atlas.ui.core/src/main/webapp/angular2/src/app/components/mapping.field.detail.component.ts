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

import { Component, Input } from '@angular/core';

import { ConfigModel } from '../models/config.model';
import { Field } from '../models/field.model';
import { DocumentDefinition } from '../models/document.definition.model';

@Component({
	selector: 'mapping-field-detail',
	template: `
	  	<div class='fieldDetail' *ngIf="docDef && docDef.fields" style="margin-bottom:5px;">
	  		<select style="width:94%; float:left;" [ngModel]="selectedFieldPath" (change)="selectionChanged($event)">
      			<option *ngFor="let f of docDef.getTerminalFields()" [ngValue]="f.path" [attr.fieldPath]="f.path">{{getFieldDisplay(f)}}</option>
   			 </select>
   			<a style='display:inline; float:right;' (click)="remove($event)"><i class="fa fa-trash" aria-hidden="true"></i></a>
   			<div style="clear:both; height:0px;">&nbsp;</div>
	  	</div>
    `
})

export class MappingFieldDetailComponent { 	
	@Input() cfg: ConfigModel;
	@Input() selectedFieldPath: string;
	@Input() docDef: DocumentDefinition;  

	public getFieldDisplay(field: Field) {
		return this.cfg.showMappingDataType ? field.path : (field.path + " (" + field.type + ")");
	} 

	remove(event: MouseEvent): void {
		this.cfg.mappingService.removeMappedField(this.selectedFieldPath, this.docDef.isInput);
	}

	selectionChanged(event: MouseEvent):void {	
		this.cfg.mappingService.removeMappedField(this.selectedFieldPath, this.docDef.isInput);
		var eventTarget: any = event.target; //extract this to avoid compiler error about 'selectedOptions' not existing.	
		this.selectedFieldPath = eventTarget.selectedOptions.item(0).attributes.getNamedItem("fieldPath").value;
		this.cfg.mappingService.addMappedField(this.selectedFieldPath, this.docDef.isInput);
	}
}
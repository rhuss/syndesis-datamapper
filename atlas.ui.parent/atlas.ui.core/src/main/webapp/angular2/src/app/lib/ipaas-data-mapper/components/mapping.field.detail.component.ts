import { Component, Input } from '@angular/core';

import { ConfigModel } from '../models/config.model';
import { Field } from '../models/field.model';
import { DocumentDefinition } from '../models/document.definition.model';

@Component({
	selector: 'mapping-field-detail',
	template: `
	  	<div class='fieldDetail' *ngIf="docDef && docDef.fields" style="margin-bottom:5px;">
	  		<select style="width:94%; float:left;" [ngModel]="selectedFieldPath" (change)="selectionChanged($event)">
      			<option *ngFor="let f of docDef.getTerminalFields(true)" [ngValue]="f.path" [attr.fieldPath]="f.path">{{getFieldDisplay(f)}}</option>
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

	public getFieldDisplay(field: Field): string {
		if (field.path == "[None]") {
			return field.path;
		}
		return this.cfg.showMappingDataType ? field.path : (field.path + " (" + field.type + ")");
	} 

	remove(event: MouseEvent): void {
		this.cfg.mappingService.removeMappedField(this.selectedFieldPath, this.docDef.isInput);
		this.cfg.mappingService.saveCurrentMapping();
	}

	selectionChanged(event: MouseEvent):void {	
		this.cfg.mappingService.removeMappedField(this.selectedFieldPath, this.docDef.isInput);
		var eventTarget: any = event.target; //extract this to avoid compiler error about 'selectedOptions' not existing.	
		this.selectedFieldPath = eventTarget.selectedOptions.item(0).attributes.getNamedItem("fieldPath").value;
		if (this.selectedFieldPath != this.docDef.getNoneField().path) {
			this.cfg.mappingService.addMappedField(this.selectedFieldPath, this.docDef.isInput);
		}
		console.log("Attempting to save current mapping, mapping detail selection changed.");
		this.cfg.mappingService.saveCurrentMapping();
	}
}
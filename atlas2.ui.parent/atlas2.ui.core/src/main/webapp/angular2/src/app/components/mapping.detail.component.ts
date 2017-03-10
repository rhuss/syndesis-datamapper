import { Component, Input, ViewChildren, Injectable, QueryList } from '@angular/core';
import { DomSanitizer, SafeResourceUrl, SafeUrl, SafeStyle} from '@angular/platform-browser';

import { ConfigModel } from '../models/config.model';
import { Field } from '../models/field.model';
import { MappingModel } from '../models/mapping.model';
import { TransitionModel, TransitionMode, TransitionDelimiter } from '../models/transition.model';
import { DocumentDefinition } from '../models/document.definition.model';

import { MappingManagementService } from '../services/mapping.management.service';
import { DocumentManagementService } from '../services/document.management.service';

import { MappingFieldDetailComponent } from './mapping.field.detail.component';
import { MappingFieldActionComponent } from './mapping.field.action.component';
import { ModalWindowComponent } from './modal.window.component';
import { TransitionSelectionComponent } from './transition.selection.component';

@Component({
	selector: 'mapping-detail',
	template: `
	  	<div class='fieldMappingDetail'  [attr.style]="detailStyle" 
	  		*ngIf="cfg.mappings.activeMapping && cfg.showMappingDetailTray">
	  		<div class="card-pf-title" style="margin:0px;">
	  			<div style="float:left;"><h2 style="display:inline;">Data Transformation</h2></div>
	  			<div style="float:right; text-align:right; padding-right:2px;">
	  			<a (click)="removeMapping($event)" style="font-size:14px;" tooltip="Remove current mapping">
	  				<i class="fa fa-trash" aria-hidden="true" style="font-size:14px; margin-left:5px;"></i> 
	  			</a>
	  			<a (click)="toggleDataTypeVisibility($event)" tooltip="Show field data types" style="margin-left:5px;">
	  				<i style="font-size:14px;" class="fa fa-cog" aria-hidden="true"></i>
	  			</a>
	  			<a (click)="deselectMapping($event)" tooltip="Deselect current mapping" style="margin-left:5px;">
	  				<i style="font-size:16px;" class="fa fa-close" aria-hidden="true"></i>
	  			</a>	  	
	  			</div>
	  			<div style="clear:both; height:0px;"></div>
	  		</div>
	  		<div class="mappingFieldContainer">
		  		<h3 style="font-size:12px; font-weight:bold;">Source</h3>
		  		<div *ngFor="let field of getMappingFields(true)" style="padding-bottom:10px;">
		  			<mapping-field-detail #mappingField [selectedFieldPath]="field.path" [cfg]="cfg"
		  				[docDef]="cfg.inputDoc"></mapping-field-detail>
		  			<mapping-field-action [field]="field" [mapping]="cfg.mappings.activeMapping" 
		  				[isInput]="true"></mapping-field-action>
		  		</div>
				<!-- <a (click)="addField($event, true)"><i class="fa fa-plus" 
					aria-hidden="true" style="font-size:10px"></i> Add Field</a> -->
			</div>
	  		<div class="mappingFieldContainer" >
	  			<h3 class="sectionHeader" style="float:left; margin-bottom:10px;">Action:&nbsp;</h3>
	  			<transition-selector style="float:left; clear:left; width:100%;" [cfg]="cfg"></transition-selector>
	  			<div style="clear:both; height:0px;"></div>
	  		</div>
	  		<div class="mappingFieldContainer">
		  		<h3 style="font-size:12px; font-weight:bold;">Target</h3>
		  		<div *ngFor="let field of getMappingFields(false)" style="padding-bottom:10px;">
		  			<mapping-field-detail #mappingField [selectedFieldPath]="field.path" [cfg]="cfg"
		  				[docDef]="cfg.outputDoc"></mapping-field-detail>
		  			<mapping-field-action [field]="field" [mapping]="cfg.mappings.activeMapping" 
		  				[isInput]="false"></mapping-field-action>
		  		</div>
				<a (click)="addField($event, false)"><i class="fa fa-plus" 
					aria-hidden="true" style="font-size:10px"></i> Add Field</a>	  		
			</div>		  				  		
	  		<a class='button' (click)="saveMapping($event)"><i class="fa fa-save" aria-hidden="true"></i> Save</a>
	    </div>
    `
})

export class MappingDetailComponent { 
	@Input() cfg: ConfigModel;
	@Input() modalWindow: ModalWindowComponent;

	private detailStyle: SafeStyle;

	@ViewChildren('mappingField') mappingFields: QueryList<MappingFieldDetailComponent>;

	constructor(private sanitizer: DomSanitizer) {}

	private getMappingFields(isInput: boolean): Field[] {
		var docDef: DocumentDefinition = isInput ? this.cfg.inputDoc : this.cfg.outputDoc;
		var fieldPaths: string[] = isInput ? this.cfg.mappings.activeMapping.inputFieldPaths
			: this.cfg.mappings.activeMapping.outputFieldPaths;
		return docDef.getFields(fieldPaths);
	}

	private deselectMapping(event: MouseEvent): void {
		this.cfg.mappingService.deselectMapping();
	}

	private toggleDataTypeVisibility(event: MouseEvent) {
		this.cfg.showMappingDataType = !this.cfg.showMappingDataType;
	}

	private saveMapping(event: MouseEvent): void {
		this.cfg.mappingService.saveMapping(this.cfg.mappings.activeMapping);
	}	

	private removeMapping(event: MouseEvent): void {
		this.cfg.mappingService.removeMapping(this.cfg.mappings.activeMapping);
	}

	private addField(event: MouseEvent, isInput: boolean): void {
		this.cfg.mappingService.addMappedField(null, isInput);
	}	

	public updateHeight(): void {
		if (this.cfg.inputDoc && this.cfg.outputDoc) {
			var maxFieldCount: number = Math.max(this.cfg.inputDoc.fields.length, 
				this.cfg.outputDoc.fields.length);
			var heightCSS: string = ((maxFieldCount * 40) + 120).toString() + "px";
			this.detailStyle = this.sanitizer.bypassSecurityTrustStyle("height:" + heightCSS + ";");
		}		
	}
}
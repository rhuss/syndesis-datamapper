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

import { Component, OnInit, Input, ViewChild, Injectable } from '@angular/core';

import { Field } from '../models/field.model';
import { DocumentDefinition } from '../models/document.definition.model';
import { MappingModel } from '../models/mapping.model';
import { TransitionModel, TransitionMode, TransitionDelimiter } from '../models/transition.model';
import { MappingDefinition } from '../models/mapping.definition.model';
import { ConfigModel } from '../models/config.model';

import { MappingManagementService } from '../services/mapping.management.service';
import { DocumentManagementService } from '../services/document.management.service';
import { ErrorHandlerService } from '../services/error.handler.service';

import { DocumentDefinitionComponent } from './document.definition.component';
import { MappingDetailComponent } from './mapping.detail.component';
import { ModalWindowComponent } from './modal.window.component';
import { DataMapperErrorComponent } from './data.mapper.error.component';
import { TransitionSelectionComponent } from './transition.selection.component';
import { LineMachineComponent } from './line.machine.component';
import { DocumentFieldDetailComponent } from './document.field.detail.component';
import { MappingSelectionComponent } from './mapping.selection.component';
import { ToolbarComponent } from './toolbar.component';

@Component({
  selector: 'data-mapper',
  template: `
  	<toolbar [buttonClickedHandler]="buttonClickedHandler" #toolbarComponent [cfg]="cfg"></toolbar>
  	<div style='height:100%;'>
  		<div class="row"><data-mapper-error #errorPanel [errorService]="cfg.errorService"></data-mapper-error></div>
  		<div class="row">
	  		<div class="col-md-12"><modal-window #modalWindow></modal-window></div>
  		</div>
  		<div class="row" style='height:100%;'>
	  		<div class="col-md-9">  		
	  			<div style="float:left; width:40%; padding-left:10px;">
		  			<document-definition #docDefInput [cfg]="cfg"
		  				[docDef]="cfg.inputDoc" [lineMachine]="lineMachine"></document-definition>
		  		</div>
		  		<div style="float:left; width:20%; margin-top:20px">
		  			<line-machine #lineMachine [cfg]="cfg" 
		  				[docDefInput]="docDefInput" [docDefOutput]="docDefOutput"></line-machine>
		  		</div>
		  		<div style="float:left; width:40%; padding-right:10px;">
		  			<document-definition #docDefOutput [cfg]="cfg"
		  				[docDef]="cfg.outputDoc" [lineMachine]="lineMachine"></document-definition>
		  		</div>
		  		<div style="clear:both; height:0px;">&nbsp;</div>
		  	</div>
		  	<div class="col-md-3" style="padding:0px;">
		  		<mapping-detail #mappingDetailComponent [cfg]="cfg"></mapping-detail>
		  	</div>
		 </div>
  	</div>
  `
})

export class DataMapperAppComponent implements OnInit {

	/*
	- fix FIXME and TODO everywhere
	*/

	@Input() cfg:ConfigModel;

	@ViewChild('lineMachine')
  	private lineMachine: LineMachineComponent;

	@ViewChild('errorPanel')
  	private errorPanel: DataMapperErrorComponent;

  	@ViewChild('modalWindow')
  	private modalWindow: ModalWindowComponent;

	@ViewChild('docDefInput')
  	private docDefInput: DocumentDefinitionComponent;

  	@ViewChild('docDefOutput')
  	private docDefOutput: DocumentDefinitionComponent;

  	@ViewChild('mappingDetailComponent')
  	private mappingDetailComponent: MappingDetailComponent;

  	@ViewChild('toolbarComponent')
  	private toolbarComponent: ToolbarComponent;

	ngOnInit(): void {				
		this.toolbarComponent.parentComponent = this;		
		this.mappingDetailComponent.modalWindow = this.modalWindow;

		this.cfg.mappingService.mappingSelectionRequired$.subscribe((mappings: MappingModel[]) => {
			this.selectMapping(mappings);
		});		
	}        

	private selectMapping(mappingsForField: MappingModel[]): void {
		this.modalWindow.reset();
		this.modalWindow.parentComponent = this;
		this.modalWindow.headerText = "Select Mapping";
		this.modalWindow.nestedComponentInitializedCallback = (mw: ModalWindowComponent) => {
			var self: DataMapperAppComponent = mw.parentComponent as DataMapperAppComponent;
			var c: MappingSelectionComponent = mw.nestedComponent as MappingSelectionComponent;
			c.mappings = mappingsForField;
			c.selectedMapping = mappingsForField[0];
		};
		this.modalWindow.nestedComponentType = MappingSelectionComponent;	
		this.modalWindow.okButtonHandler = (mw: ModalWindowComponent) => {
			var self: DataMapperAppComponent = mw.parentComponent as DataMapperAppComponent;
			var c: MappingSelectionComponent = mw.nestedComponent as MappingSelectionComponent;
			var mapping: MappingModel = c.selectedMapping;
			self.cfg.mappingService.selectMapping(mapping, false);
		};
		this.modalWindow.cancelButtonHandler = (mw: ModalWindowComponent) => {
			var self: DataMapperAppComponent = mw.parentComponent as DataMapperAppComponent;	
			self.cfg.mappingService.selectMapping(null, false);
		};
		this.modalWindow.show();
	}	

	public updateFromConfig(): void {
		this.lineMachine.updateHeight();
		this.mappingDetailComponent.updateHeight();
		
		// update the mapping line drawing after our fields have redrawn themselves
        // without this, the x/y from the field dom elements is messed up / misaligned.
        setTimeout(()=> { this.lineMachine.redrawLinesForMappings(); }, 1);        
	}

	public buttonClickedHandler(action: string, component: ToolbarComponent): void {
		var self = component.parentComponent as DataMapperAppComponent;
		if ("add" == action) {
	    	console.log("Creating new mapping.")
			self.cfg.mappings.activeMapping = self.cfg.mappingService.createMapping();
			self.cfg.showMappingDetailTray = true;
		} else if ("showDetails" == action) {
			self.cfg.showMappingDetailTray = !self.cfg.showMappingDetailTray;
		}
	}
}

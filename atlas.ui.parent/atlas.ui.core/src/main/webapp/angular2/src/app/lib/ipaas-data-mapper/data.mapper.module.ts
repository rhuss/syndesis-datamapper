import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
/* import { BrowserModule } from '@angular/platform-browser'; */
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { DocumentManagementService } from './services/document.management.service';
import { MappingManagementService } from './services/mapping.management.service';
import { ErrorHandlerService } from './services/error.handler.service';

import { ModalWindowComponent } from './components/modal.window.component';
import { TransitionSelectionComponent } from './components/transition.selection.component';
import { DataMapperErrorComponent } from './components/data.mapper.error.component';

import { DocumentFieldDetailComponent } from './components/document.field.detail.component';
import { DocumentDefinitionComponent } from './components/document.definition.component';

import { MappingFieldDetailComponent } from './components/mapping.field.detail.component';
import { MappingFieldActionComponent } from './components/mapping.field.action.component';
import { MappingDetailComponent } from './components/mapping.detail.component';
import { MappingSelectionComponent } from './components/mapping.selection.component';

import { DataMapperAppComponent } from './components/data.mapper.app.component';
import { DataMapperAppExampleHostComponent } from './components/data.mapper.example.host.component';

import { LineMachineComponent } from './components/line.machine.component';
import { ToolbarComponent } from './components/toolbar.component';

@NgModule({
	imports: [ CommonModule, /* BrowserModule, */ HttpModule, FormsModule ],
	declarations: [ DataMapperAppComponent, DocumentDefinitionComponent, 
		MappingDetailComponent, ModalWindowComponent, DataMapperAppExampleHostComponent,
		MappingFieldActionComponent, MappingFieldDetailComponent, DocumentFieldDetailComponent,
		DataMapperErrorComponent, TransitionSelectionComponent, LineMachineComponent,
		MappingSelectionComponent, ToolbarComponent ],
	exports: [ DataMapperAppExampleHostComponent, ModalWindowComponent ],
	providers: [ DocumentManagementService, MappingManagementService, ErrorHandlerService ],
	entryComponents: [ TransitionSelectionComponent, MappingSelectionComponent ],
	bootstrap: [ DataMapperAppExampleHostComponent ]
})
export class DataMapperModule { }
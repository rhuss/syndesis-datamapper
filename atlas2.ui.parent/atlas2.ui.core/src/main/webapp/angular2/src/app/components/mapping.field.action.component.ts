import { Component, Input } from '@angular/core';

import { Field } from '../models/field.model';
import { MappingModel } from '../models/mapping.model';

@Component({
	selector: 'mapping-field-action',
	template: `
		<div *ngIf="isInput == false && mapping.transition.isSeparateMode()" class="form-group" style="margin-right:22px;">
			<div style="float:right">
				<label style="width:50px;">Index:</label>
				<input type="text" [(ngModel)]="mapping.fieldSeparatorIndexes[field.path]" 
					style="width:50px; text-align:right;"/>
			</div>
			<div style="clear:both; height:0px;"></div>
		</div>
	`
})

export class MappingFieldActionComponent { 
	@Input() field: Field;
	@Input() mapping: MappingModel;	
	@Input() isInput: boolean = false;
}
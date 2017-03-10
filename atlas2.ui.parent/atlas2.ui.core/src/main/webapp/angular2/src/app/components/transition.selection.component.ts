import { Component, Input } from '@angular/core';

import { TransitionModel, TransitionMode, TransitionDelimiter } from '../models/transition.model';
import { ConfigModel } from '../models/config.model';

@Component({
	selector: 'transition-selector',
	template: `
		<div class="TransitionSelector" *ngIf="cfg.mappings.activeMapping">
			<div class="form-group">
				<label>Action:</label>
				<select (change)="selectionChanged($event);" selector="mode" 
					[ngModel]="cfg.mappings.activeMapping.transition.mode">
					<option value="{{modes.MAP}}">Map</option>
					<option value="{{modes.SEPARATE}}">Separate</option>
				</select>
			</div>
			<div class="form-group" *ngIf="cfg.mappings.activeMapping.transition.mode == modes.SEPARATE">
				<label>Separator:</label>
				<select (change)="selectionChanged($event);" selector="separator" 
					[ngModel]="cfg.mappings.activeMapping.transition.delimiter">
					<option value="{{delimeters.SPACE}}">Space</option>
					<option value="{{delimeters.COMMA}}">Comma</option>
				</select>
			</div>
		</div>
	`
})

export class TransitionSelectionComponent {
	@Input() cfg: ConfigModel;

	private modes: any = TransitionMode;
	private delimeters: any = TransitionDelimiter;	

	selectionChanged(event: MouseEvent) {
		var eventTarget: any = event.target; //extract this to avoid compiler error about 'selectedOptions' not existing.	
		var selectorIsMode: boolean = "mode" == eventTarget.attributes.getNamedItem("selector").value
		var selectedValue: any = eventTarget.selectedOptions.item(0).attributes.getNamedItem("value").value;
		if (selectorIsMode) {
			this.cfg.mappings.activeMapping.transition.mode = parseInt(selectedValue);
		} else {
			this.cfg.mappings.activeMapping.transition.delimiter = parseInt(selectedValue);
		}	
		this.cfg.mappings.activeMapping.updateSeparatorIndexes();	
	}
}
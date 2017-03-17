import { Component, Input } from '@angular/core';
import { MappingModel } from '../models/mapping.model';

@Component({
	selector: 'mapping-selection',
	template: `
		<div class="MappingSelectionComponent" *ngIf="selectedMapping">
			<table>
				<tr class="headers">
					<th class="radios"></th>
					<th class="inputFields">Source</th>
					<th class="outputFields">Target</th>
				</tr>
				<tr *ngFor="let m of mappings; let i = index; let odd=odd; let even=even;" 
					[class]="odd ? 'odd' : 'even'">				
					<td class="radios">
						<input name="options" type="radio" 
							[attr.value]="m.uuid"
							[attr.checked]="selectedMapping.uuid == m.uuid ? 'checked' : null"
							(click)="selectionChanged($event)" /><br/>
					</td>
					<td class="inputFields">
						<div class="inputField" *ngFor="let field of m.inputFieldPaths">{{field}}</div>
					</td>
					<td class="outputFields">
						<div class="outputField" *ngFor="let field of m.outputFieldPaths">{{field}}</div>
					</td>
				</tr>	
			</table>		
		</div>
	`
})

export class MappingSelectionComponent {
	@Input() mappings: MappingModel[];
	@Input() selectedMapping: MappingModel;

	selectionChanged(event: MouseEvent) {
		var eventTarget: any = event.target;
		for (let m of this.mappings) {
			if (m.uuid == eventTarget.value) {
				this.selectedMapping = m;
				break;
			}
		}
	}
}
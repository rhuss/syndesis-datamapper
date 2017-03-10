import { Field } from './field.model';

import { TransitionModel, TransitionMode } from './transition.model';

export class MappingModel {
	public uuid: string;
	public inputFieldPaths: string[] = [];
	public outputFieldPaths: string[] = [];		
	public saved: boolean = false;
	public transition: TransitionModel = new TransitionModel();	
	public fieldSeparatorIndexes: { [key:string]:string; } = {};

	public updateSeparatorIndexes(): void {
		var isSeparateMapping: boolean = (this.transition.mode == TransitionMode.SEPARATE);
		for (let fieldPath of this.inputFieldPaths.concat(this.outputFieldPaths)) {
			if (this.fieldSeparatorIndexes[fieldPath] == null) {
				this.fieldSeparatorIndexes[fieldPath] = "1";
			}
		}
	}
}
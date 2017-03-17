import { Field } from './field.model';
import { MappingModel } from './mapping.model';

export class MappingDefinition {
	mappingUUID: string = null;
	mappings: MappingModel[] = [];	
	activeMapping: MappingModel = null;
}
import { MappingDefinition } from './mapping.definition.model';
import { DocumentDefinition } from './document.definition.model';

import { ErrorHandlerService } from '../services/error.handler.service';
import { DocumentManagementService } from '../services/document.management.service';
import { MappingManagementService } from '../services/mapping.management.service';

export class ConfigModel {
	public baseJavaServiceUrl: string;
	public baseMappingServiceUrl: string;
	public mappingInputJavaClass: string;
	public mappingOutputJavaClass: string;

	public showMappingDetailTray: boolean = false;
	public showMappingDataType: boolean = false;
	public showLinesAlways: boolean = false;

	public documentService: DocumentManagementService;
	public mappingService: MappingManagementService;
	public errorService: ErrorHandlerService;	

	public inputDoc: DocumentDefinition;
	public outputDoc: DocumentDefinition;

	public mappings: MappingDefinition = new MappingDefinition();
}
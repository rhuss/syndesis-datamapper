export class Field {
	name: string;
    displayName: string;
	path: string;
	type: string;
	serviceObject: any;
	parentField: Field;
	partOfMapping: boolean = false;	
	visible:boolean = true;
	selected:boolean = false;
	children: Field[] = [];
    fieldDepth: number = 0;
    uuid: string;
    static uuidCounter: number = 0;
    collapsed: boolean = true;

    constructor() {
        this.uuid = Field.uuidCounter.toString();
        Field.uuidCounter++;
    }
	
    public isTerminal(): boolean {
    	return (this.children == null || this.children.length == 0);
    }

    public copy(): Field {
    	var copy: Field = new Field();
    	copy.name = this.name;
    	copy.displayName = this.displayName;
        copy.path = this.path;
    	copy.type = this.type;
    	copy.serviceObject = new Object();
        for (var property in this.serviceObject) {
            copy.serviceObject[property] = this.serviceObject[property];
        }
    	copy.parentField = null;
    	copy.partOfMapping = this.partOfMapping;
    	copy.visible = this.visible;
    	copy.selected = this.selected;
    	for (let childField of this.children) {
    		copy.children.push(childField.copy());
    	}
    	return copy;
    }
}
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
	
    public isTerminal(): boolean {
    	return (this.children == null || this.children.length == 0);
    }

    public copy(): Field {
    	var copy: Field = new Field();
    	copy.name = this.name;
    	copy.displayName = this.displayName;
        copy.path = this.path;
    	copy.type = this.type;
    	copy.serviceObject = this.serviceObject;
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
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

import { Field } from './field.model';
import { MappingModel } from './mapping.model';

export class DocumentDefinition {
	name: string;
	public fields: Field[] = [];
    public allFields: Field[] = [];
    public terminalFields: Field[] = [];
    isInput: boolean;
    public complexFieldsByClassName: { [key:string]:Field; } = {};
    enumFieldClasses: string[] = [];
    public debugParsing: boolean = false;    
    public fieldsByPath: { [key:string]:Field; } = {};

    public getCachedField(name: string): Field {
        return this.complexFieldsByClassName[name];
    }

    public getAllFields(): Field[] {
        return [].concat(this.allFields);
    }

    public getFields(fieldPaths: string[]): Field[] {
        var fields: Field[] = [];
        for (let fieldPath of fieldPaths) {
            var field: Field = this.getField(fieldPath);
            if (field != null) {
                fields.push(field);
            }
        }
        return fields;
    }
	
    public getField(fieldPath: string): Field {
        return this.fieldsByPath[fieldPath];
    }   

    public getTerminalFields(): Field[] {
        return [].concat(this.terminalFields);
    }

    public clearSelectedFields(): void {
        for (let field of this.allFields) {
            field.selected = false;
        }
    }

    public getSelectedFields(): Field[] {
        var fields: Field[] = [];
        for (let field of this.allFields) {
            if (field.selected) {
                fields.push(field);
            }
        }
        return fields;
    }

    public selectFields(fieldPaths: string[]): void {
        for (let fieldPath of fieldPaths) {
            var field: Field = this.getField(fieldPath);
            if (field != null) {
                field.selected = true;
            }
        }
    }  

    public populateFromFields(): void {
        //TODO: put enums back in after demo
        this.filterEnumFields(this.fields);

        this.populateComplexFields();

        this.alphabetizeFields(this.fields);

        for (let field of this.fields) {
            this.populateFieldParentPaths(field, "", 0);
            this.populateFieldData(field);
        }       

        if (this.debugParsing) {
            console.log(this.printDocumentFields(this.fields, 0));
        }

        console.log("Finished populating fields for '" + this.name + "', field count: " + this.allFields.length + ", terminal: " + this.terminalFields.length + ".");
    }

    private alphabetizeFields(fields: Field[]) {
        var fieldsByName: { [key:string]:Field; } = {};
        var fieldNames: string[] = [];
        for (let field of fields) {
            var name: string = field.name;
            var firstCharacter: string = name.charAt(0).toUpperCase();
            name = firstCharacter + name.substring(1);
            field.displayName = name;
            //if field is a dupe, discard it
            if (fieldsByName[name] != null) {
                continue;
            }
            fieldsByName[name] = field;
            fieldNames.push(name);
        }
        fieldNames.sort();
        fields.length = 0;
        for (let name of fieldNames) {
            fields.push(fieldsByName[name]);
        }

        for (let field of fields) {
            if (field.children && field.children.length) {
                this.alphabetizeFields(field.children);
            }
        }
    }

    private populateFieldParentPaths(field: Field, parentPath: string, depth: number): void {
        field.path = parentPath + field.displayName;
        field.fieldDepth = depth;
        for (let childField of field.children) {
            childField.parentField = field;
            this.populateFieldParentPaths(childField, parentPath + field.displayName + ".", depth + 1);
        }
    }

    private populateFieldData(field:Field) {
        this.allFields.push(field);
        this.fieldsByPath[field.path] = field;
        if (field.children.length == 0) {
            this.terminalFields.push(field);
        } else {
            for (let childField of field.children) {
                this.populateFieldData(childField);
            }
        }
    }

    private filterEnumFields(fields: Field[]) {
        var fieldsCopy: Field[] = [].concat(fields);
        fields.length = 0;
        for (let field of fieldsCopy) {
            if ((field.serviceObject.enumeration == true)
                || (this.enumFieldClasses.indexOf(field.serviceObject.className) != -1)) {
                console.log("Filtering out enum field: " + field.name + " (" + field.serviceObject.className + ")");
                continue;
            }
            fields.push(field);
            this.filterEnumFields(field.children);
        }
    }

    private populateComplexFields() {
        var fields: Field[] = this.fields;

        //build complex field cache
        this.discoverComplexFields(fields);

        //remove children more than one level deep in cached fields
        for (let key in this.complexFieldsByClassName) {
            var cachedField: Field = this.complexFieldsByClassName[key];
            for (let childField of cachedField.children) {
                childField.children = [];
            }
        }

        // print cached complex fields
        if (this.debugParsing) {
            var result: string = "Cached Fields: ";
            for (let key in this.complexFieldsByClassName) {
                var cachedField: Field = this.complexFieldsByClassName[key];
                result +=  cachedField.name + " " + cachedField.type + " " + cachedField.serviceObject.status 
                    + " (" + cachedField.serviceObject.className + ") children:" + cachedField.children.length + "\n";
            }
            console.log(result);
        }

        //remove children more than one layer deep in root fields
        for (let field of fields) {
            for (let childField of field.children) {
                childField.children = [];
            }
        }

        //populate complex fields
        this.populateComplexFieldInternal(fields, 1);
    }    

    private populateComplexFieldInternal(fields: Field[], depth: number) {
        for (var i = 0; i < fields.length; i++) {        
            var field: Field = fields[i];
            if (field.type != "COMPLEX") {
                continue;
            }
            
            var cachedField = this.getCachedField(field.serviceObject.className);
            if (cachedField == null) {
                console.error("ERROR: Couldn't find cached complex field: " + field.serviceObject.className);
                continue;
            }

            //copy cached field
            cachedField = cachedField.copy();
            cachedField.name = field.name;
            if (depth < 2) {
                //TODO: after demo fix recursion here
                this.populateComplexFieldInternal(cachedField.children, depth + 1);          
            }
            fields[i] = cachedField;
        }
    }

    private discoverComplexFields(fields: Field[]): void {
        for (let field of fields) {
            if (field.type != "COMPLEX") {
                continue;
            }
            if (field.serviceObject.status == "SUPPORTED") {
                this.complexFieldsByClassName[field.serviceObject.className] = field.copy();
            }
            if (field.children) {
                this.discoverComplexFields(field.children);
            }
        }
    }

    private printDocumentFields(fields: Field[], indent: number): string {
        var result: string = "";
        for (let f of fields) {
            if (f.type != "COMPLEX") {
                continue;
            }
            for (var i = 0; i < indent; i++) {
                result += "\t";
            }
            result += f.name + " " + f.type + " " + f.serviceObject.status + " (" + f.serviceObject.className + ") children:" + f.children.length;
            result += "\n";
            if (f.children) {
                result += this.printDocumentFields(f.children, indent + 1);
            }
        }
        return result;
    }

    public updateFromMappings(mappings: MappingModel[]): void {
        for (let field of this.allFields) {
            field.partOfMapping = false;
        }
        for (let mapping of mappings) {
            var fieldPaths: string[] = this.isInput ? mapping.inputFieldPaths : mapping.outputFieldPaths;
            for (let field of this.getFields(fieldPaths)) {
                field.partOfMapping = true;   
                var parentField: Field = field.parentField;
                while (parentField != null) {
                    parentField.partOfMapping = true; 
                    parentField = parentField.parentField;
                }
            }
        }
    }
}
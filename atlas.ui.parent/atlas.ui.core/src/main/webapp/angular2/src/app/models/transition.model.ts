export enum TransitionMode { MAP, SEPARATE }    
export enum TransitionDelimiter { SPACE, COMMA }    

export class TransitionModel {
	public uuid: string;
	public mode: TransitionMode = TransitionMode.MAP;
	public delimiter: TransitionDelimiter = TransitionDelimiter.SPACE;

	public getPrettyName() {
		if (this.mode == TransitionMode.SEPARATE) {
			return "Separate (" + ((this.delimiter == TransitionDelimiter.SPACE) ? "Space)" : "Comma)");
		}
		return "Map";
	}

	public isSeparateMode(): boolean {
		return this.mode == TransitionMode.SEPARATE;
	}
}
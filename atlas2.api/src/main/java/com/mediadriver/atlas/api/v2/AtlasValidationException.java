package com.mediadriver.atlas.api.v2;

public class AtlasValidationException extends AtlasException {

	private static final long serialVersionUID = 6537018220259702613L;
	
	public AtlasValidationException() { super(); }
	public AtlasValidationException(String message, Throwable cause) { super(message, cause); }
	public AtlasValidationException(String message) { super(message); }
	public AtlasValidationException(Throwable cause) { super(cause); }
}

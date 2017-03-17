package com.mediadriver.atlas.api.v2;

public class AtlasUnsupportedException extends AtlasException {

	private static final long serialVersionUID = 4276166328541103662L;
	
	public AtlasUnsupportedException() { super(); }
	public AtlasUnsupportedException(String message, Throwable cause) { super(message, cause); }
	public AtlasUnsupportedException(String message) { super(message); }
	public AtlasUnsupportedException(Throwable cause) { super(cause); }
}

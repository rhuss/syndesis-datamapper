package com.mediadriver.atlas.api.v2;

public class AtlasNotFoundException extends AtlasException {

	private static final long serialVersionUID = -780179923312820477L;
	public AtlasNotFoundException() { super(); }
	public AtlasNotFoundException(String message, Throwable cause) { super(message, cause); }
	public AtlasNotFoundException(String message) { super(message); }
	public AtlasNotFoundException(Throwable cause) { super(cause); }
}

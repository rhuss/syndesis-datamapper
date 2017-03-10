package com.mediadriver.atlas.api.v2;

public class AtlasException extends Exception {
	private static final long serialVersionUID = 7547364931796852076L;

	public AtlasException() { super(); }
	public AtlasException(String message, Throwable cause) { super(message, cause); }
	public AtlasException(String message) { super(message); }
	public AtlasException(Throwable cause) { super(cause); }
}

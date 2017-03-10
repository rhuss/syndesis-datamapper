package com.mediadriver.atlas.api.v2;

public interface AtlasContext {

	public AtlasSession createSession();
	public void process(AtlasSession session) throws AtlasException;

}

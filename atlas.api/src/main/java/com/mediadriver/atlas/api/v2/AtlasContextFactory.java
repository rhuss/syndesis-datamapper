package com.mediadriver.atlas.api.v2;

import java.util.Map;
import com.mediadriver.atlas.v2.AtlasMapping;

public interface AtlasContextFactory {

	public void init();
	public void destroy();
	public AtlasContext createContext(AtlasMapping atlasMapping) throws AtlasException;
	public void setProperties(Map<String, String> properties);
	public Map<String, String> getProperties();
}

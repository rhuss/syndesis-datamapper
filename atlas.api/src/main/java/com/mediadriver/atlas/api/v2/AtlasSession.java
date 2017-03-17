package com.mediadriver.atlas.api.v2;

import java.util.List;
import java.util.Map;
import com.mediadriver.atlas.v2.AtlasMapping;

public interface AtlasSession {

	public Map<String, Object> getProperties();
	public AtlasMapping getAtlasMapping();
	public void setAtlasMapping(AtlasMapping atlasMapping);
	public Object getInput();
	public void setInput(Object inputObject);
	public Object getOutput();
	public void setOutput(Object outputObject);
	public List<Map<String, Object>> getData();
	public void setData(List<Map<String, Object>> data);
	
}

package com.mediadriver.atlas.core.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mediadriver.atlas.api.v2.AtlasSession;
import com.mediadriver.atlas.v2.AtlasMapping;

public class DefaultAtlasSession implements AtlasSession {

	private AtlasMapping atlasMapping;
	private Map<String, Object> properties;
	private List<Map<String, Object>> data;
	private Object input;
	private Object output;
	
	public DefaultAtlasSession() { initialize(); }
	
	protected void initialize() { properties = new ConcurrentHashMap<String, Object>(); 
								  data = new ArrayList<Map<String, Object>>(); };
	
	public AtlasMapping getAtlasMapping() { return atlasMapping; }
	public void setAtlasMapping(AtlasMapping atlasMapping) { this.atlasMapping = atlasMapping; }
	@Override
	public Object getInput() { return input; }
	@Override
	public Object getOutput() { return output; }
	@Override
	public void setInput(Object input) { this.input = input; }
	@Override
	public void setOutput(Object output) { this.output = output; }
	public Map<String, Object> getProperties() { return this.properties; }
	public List<Map<String, Object>> getData() { return data; }
	public void setData(List<Map<String, Object>> data) { this.data = data; }
	
}

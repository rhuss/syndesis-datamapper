package com.mediadriver.atlas.javapath.v2;

import java.util.ArrayList;
import java.util.List;

public class JavaPath {

	private List<String> segments = new ArrayList<String>();
	public static final String JAVAPATH_SEPARATOR = "."; 
	public static final String JAVAPATH_SEPARATOR_ESCAPTED = "\\."; 

	
	public JavaPath() {}
	
	public JavaPath(String javaPath) {
		if(javaPath != null) {
			if(javaPath.contains(JAVAPATH_SEPARATOR)) {
				String[] parts = javaPath.split(JAVAPATH_SEPARATOR_ESCAPTED, 512);
				for(String part : parts) {
					getSegments().add(part);
				}
			}
		}
	}
	
	public JavaPath appendField(String fieldName) {
		this.segments.add(fieldName);
		return this;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		int i=0;
		for(String part : getSegments()) {
			buffer.append(part);
			if(i < (getSegments().size()-1)) {
				buffer.append(JAVAPATH_SEPARATOR);
			}
			i++;
		}
		return buffer.toString();
	}
	
	public List<String> getSegments() {
		return this.segments;
	}
}

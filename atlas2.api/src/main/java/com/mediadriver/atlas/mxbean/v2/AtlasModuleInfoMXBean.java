package com.mediadriver.atlas.mxbean.v2;

public interface AtlasModuleInfoMXBean {
	public String getName();
	public String getClassName();
	public String getModuleClassName();
	public String getVersion();
	public String[] getDataFormats();
	public String[] getPackageNames();
	public Boolean isSourceSupported();
	public Boolean isTargetSupported();
}

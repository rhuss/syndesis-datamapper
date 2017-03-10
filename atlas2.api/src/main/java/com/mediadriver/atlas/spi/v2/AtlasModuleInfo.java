package com.mediadriver.atlas.spi.v2;

public interface AtlasModuleInfo {
	public String getName();
	public String getUri();
	public String getModuleClassName();
	public String[] getDataFormats();
	public String[] getPackageNames();
	public Boolean isSourceSupported();
	public Boolean isTargetSupported();
}

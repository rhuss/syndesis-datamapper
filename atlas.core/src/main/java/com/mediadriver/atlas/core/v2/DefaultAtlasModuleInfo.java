package com.mediadriver.atlas.core.v2;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;
import com.mediadriver.atlas.mxbean.v2.AtlasModuleInfoMXBean;
import com.mediadriver.atlas.spi.v2.AtlasModuleInfo;

public class DefaultAtlasModuleInfo implements Serializable, AtlasModuleInfo, AtlasModuleInfoMXBean {
	private static final long serialVersionUID = -4604837498041897724L;
	private String name;
	private String uri;
	private Boolean sourceSupported;
	private Boolean targetSupported;
	private Class<?> moduleClass;
	private Constructor<?> constructor;
	private List<String> formats;
	private List<String> packageNames;
	    
	public DefaultAtlasModuleInfo(String name, String uri, Class<?> moduleClass, Constructor<?> constructor, List<String> formats, List<String> packageNames) {
		this.name = name;
		this.uri = uri;
		this.moduleClass = moduleClass;
	    this.constructor = constructor;        
	    this.formats = formats;
	    this.packageNames = packageNames;
	}

	public Class<?> getModuleClass() { return moduleClass; }
	public Constructor<?> getConstructor() { return constructor; }
	public List<String> getFormats() { return formats; }
	
	@Override
	public String[] getDataFormats() {
		if(formats != null)
			return formats.toArray(new String[formats.size()]);
		else 
			return new String[0];
	}

	
	@Override
	public String getModuleClassName() {
		if(moduleClass != null) {
			return moduleClass.getName();
		} else { 
			return null;
		}
	}

	@Override
	public String[] getPackageNames() {
		if(packageNames == null || packageNames.size() < 1) {
			return new String[0];
		}
		
		return packageNames.toArray(new String[packageNames.size()]);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUri() {
		return uri;
	}
	
	@Override
	public Boolean isSourceSupported() {
		return sourceSupported;
	}
	
	@Override
	public Boolean isTargetSupported() {
		return targetSupported;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

	@Override
	public String getVersion() {
		return this.getClass().getPackage().getImplementationVersion();
	}

	@Override
	public String toString() {
		return "DefaultAtlasModuleInfo [name=" + name + ", uri=" + uri + ", sourceSupported=" + sourceSupported
				+ ", targetSupported=" + targetSupported + ", moduleClass=" + moduleClass + ", constructor="
				+ constructor + ", formats=" + formats + ", packageNames=" + packageNames + "]";
	}
}

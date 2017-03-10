package com.mediadriver.atlas.spi.v2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AtlasModuleDetail {
	
	String name();
	String uri();
	String[] dataFormats();
	String[] configPackages();
	String[] modes();
}

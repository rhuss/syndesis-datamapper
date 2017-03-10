package com.mediadriver.atlas.spi.v2;

import java.util.List;

import com.mediadriver.atlas.api.v2.AtlasException;
import com.mediadriver.atlas.api.v2.AtlasSession;

public interface AtlasModule {

	public void init();
	public void destroy();
	public void processInput(AtlasSession session) throws AtlasException;
	public void processOutput(AtlasSession session) throws AtlasException;
	public AtlasModuleMode getMode();
	public void setMode(AtlasModuleMode atlasModuleMode);
	public List<AtlasModuleMode> listSupportedModes();
	public Boolean isStatisticsSupported();
	public Boolean isStatisticsEnabled();

}

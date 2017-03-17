package com.mediadriver.atlas.mock.module.v2;

import java.util.List;
import com.mediadriver.atlas.api.v2.AtlasException;
import com.mediadriver.atlas.api.v2.AtlasSession;
import com.mediadriver.atlas.spi.v2.AtlasModule;
import com.mediadriver.atlas.spi.v2.AtlasModuleDetail;
import com.mediadriver.atlas.spi.v2.AtlasModuleMode;

@AtlasModuleDetail(name="MockModule", uri="atlas:mock", modes={"SOURCE", "TARGET"}, dataFormats={"mock"}, configPackages={"com.mediadriver.atlas.v2"})
public class MockModule implements AtlasModule {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processInput(AtlasSession session) throws AtlasException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processOutput(AtlasSession session) throws AtlasException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AtlasModuleMode getMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMode(AtlasModuleMode atlasModuleMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AtlasModuleMode> listSupportedModes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isStatisticsSupported() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isStatisticsEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

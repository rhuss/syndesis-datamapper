/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mediadriver.atlas.mock.module.v2;

import com.mediadriver.atlas.api.v2.AtlasException;
import com.mediadriver.atlas.api.v2.AtlasSession;
import com.mediadriver.atlas.spi.v2.AtlasModule;
import com.mediadriver.atlas.spi.v2.AtlasModuleDetail;
import com.mediadriver.atlas.spi.v2.AtlasModuleMode;

import java.util.List;

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

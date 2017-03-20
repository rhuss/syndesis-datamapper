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

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
package com.mediadriver.atlas.api.v2;

import java.util.List;
import java.util.Map;
import com.mediadriver.atlas.v2.AtlasMapping;

public interface AtlasSession {

	public Map<String, Object> getProperties();
	public AtlasMapping getAtlasMapping();
	public void setAtlasMapping(AtlasMapping atlasMapping);
	public Object getInput();
	public void setInput(Object inputObject);
	public Object getOutput();
	public void setOutput(Object outputObject);
	public List<Map<String, Object>> getData();
	public void setData(List<Map<String, Object>> data);
	
}

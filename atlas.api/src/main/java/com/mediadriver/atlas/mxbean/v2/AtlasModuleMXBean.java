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
package com.mediadriver.atlas.mxbean.v2;

import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.TabularData;

public interface AtlasModuleMXBean {
	public String getUuid();
	public String getName();
	public String getClassName();
	public String getVersion();
	public String[] getDataFormats();
	public String[] getPackageNames();
	public String getMode();
	public boolean isSourceSupported();
	public boolean isTargetSupported();
	public boolean isStatisticsEnabled();
	public void setStatisticsEnabled(boolean enabled);
	public long getInputCount();
	public long getInputErrorCount();
	public long getInputSuccessCount();
	public long getInputMinExecutionTime();
	public long getInputMaxExecutionTime();
	public long getInputTotalExecutionTime();
	public long getOutputCount();
	public long getOutputErrorCount();
	public long getOutputSuccessCount();
	public long getOutputMinExecutionTime();
	public long getOutputMaxExecutionTime();
	public long getOutputTotalExecutionTime();
	public TabularData readAndResetStatistics() throws OpenDataException;
	
}

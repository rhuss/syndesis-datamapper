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
package com.mediadriver.atlas.runtime;

import com.mediadriver.atlas.java.inspect.v2.MavenClasspathHelper;
import com.mediadriver.atlas.java.service.v2.JavaService;
import com.mediadriver.atlas.java.v2.MavenClasspathRequest;
import com.mediadriver.atlas.java.v2.MavenClasspathResponse;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@ApplicationPath("/v2/atlas")
@Path("/java")
public class JavaServiceComponent extends JavaService {

    /**
     * Stub out mavenclasspath processing for now.
     *
     * @param request
     * @return
     * @throws Exception
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/mavenclasspath")
    public Response generateClasspath(MavenClasspathRequest request) throws Exception {
        MavenClasspathResponse response = new MavenClasspathResponse();
        response.setExecutionTime(0L);
        response.setClasspath("");
        return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .header("Access-Control-Allow-Methods", "GET,PUT,POST,PATCH,DELETE")
            .entity(response)
            .build();
    }
}

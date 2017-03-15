package com.mediadriver.atlas.runtime;

import com.mediadriver.atlas.service.v2.AtlasService;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

@Component
@ApplicationPath("/v2/atlas")
@Path("/")
public class AtlasServiceComponent extends AtlasService {

}

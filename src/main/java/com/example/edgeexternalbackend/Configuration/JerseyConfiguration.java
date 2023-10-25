package com.example.edgeexternalbackend.Configuration;

import com.example.edgeexternalbackend.Controllers.EdgeController;
import com.example.edgeexternalbackend.Controllers.LoginController;
import com.example.edgeexternalbackend.Filter.CorsFilter;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/v1")
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void init() {
        register(CorsFilter.class);
        register(EdgeController.class);
        register(LoginController.class);
    }
}
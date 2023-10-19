package com.example.edgeexternalbackend.Modal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdgeApiProperties {

    @Value("${edgeApi.externalUrl}")
    private String externalEdgeApiUrl;

    @Value("${edgeApi.internalUrl}")
    private String internalEdgeApiUrl;

    public String getExternalEdgeApiUrl() {
        return externalEdgeApiUrl;
    }

    public String getInternalEdgeApiUrl() {
        return internalEdgeApiUrl;
    }
}

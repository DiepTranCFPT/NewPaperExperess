package com.experess.news.securityconfig;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EndpointsConfig {

    public List<String> publicEndpoints;

    @JsonProperty("PublicEndpoints")
    public void setPublicEndpoints(List<String> publicEndpoints) {
        this.publicEndpoints = publicEndpoints;
    }

    public static EndpointsConfig getEndpointsConfig() throws IOException {
        return new ObjectMapper()
                .readValue(EndpointsConfig.class.getResourceAsStream("EndpointsConfig.json"),
                        EndpointsConfig.class);
    }

}

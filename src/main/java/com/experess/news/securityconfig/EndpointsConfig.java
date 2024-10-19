package com.experess.news.securityconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class EndpointsConfig {

    private List<String> publicEndpoints;

    @JsonProperty("PublicEndpoints")
    public void setPublicEndpoints(List<String> publicEndpoints) {
        this.publicEndpoints = publicEndpoints;
    }

    public List<String> getPublicEndpoints() {
        return publicEndpoints;
    }

    public static EndpointsConfig getEndpointsConfig() throws IOException {
        try (InputStream inputStream = EndpointsConfig.class.getResourceAsStream("/EndpointsConfig.json")) {
            if (inputStream == null) {
                throw new IOException("Tệp EndpointsConfig.json không tìm thấy");
            }
            return new ObjectMapper().readValue(inputStream, EndpointsConfig.class);
        }
    }

    @PostConstruct
    public void init() throws IOException {
        EndpointsConfig config = getEndpointsConfig();
        this.publicEndpoints = config.getPublicEndpoints();
    }
}

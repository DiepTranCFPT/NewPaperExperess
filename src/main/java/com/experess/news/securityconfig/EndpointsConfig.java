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

    @PostConstruct
    public void init() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/EndpointsConfig.json")) {
            if (inputStream == null) {
                throw new IOException("Không tìm thấy tệp EndpointsConfig.json");
            }
            EndpointsConfig config = new ObjectMapper().readValue(inputStream, EndpointsConfig.class);
            this.publicEndpoints = config.getPublicEndpoints();
        }
    }
}

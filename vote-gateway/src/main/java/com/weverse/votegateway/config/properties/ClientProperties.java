package com.weverse.votegateway.config.properties;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.net.URI;

@ConfigurationProperties(prefix = "vote")
public record ClientProperties(
        @NotNull
        URI processServiceUri
) {}

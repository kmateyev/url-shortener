package com.kmateyev.url.shortener.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(String secret, Long expiration) {
}

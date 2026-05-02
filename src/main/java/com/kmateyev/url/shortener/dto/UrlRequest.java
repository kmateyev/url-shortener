package com.kmateyev.url.shortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class UrlRequest {

    @URL
    @NotBlank(message = "URL is required")
    private String originalUrl;
}

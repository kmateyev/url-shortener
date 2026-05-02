package com.kmateyev.url.shortener.dto;

import com.kmateyev.url.shortener.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlResponse extends BaseResponse {

    private String shortCode;
    private String shortUrl;
    private String originalUrl;
}

package com.kmateyev.url.shortener.dto;

import com.kmateyev.url.shortener.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OriginalUrlResponse extends BaseResponse {

    private String originalUrl;
}

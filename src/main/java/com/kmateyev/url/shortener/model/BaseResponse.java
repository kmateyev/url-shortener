package com.kmateyev.url.shortener.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private Integer code;

    private String message;
}

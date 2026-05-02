package com.kmateyev.url.shortener.service;

import com.kmateyev.url.shortener.dto.OriginalUrlResponse;
import com.kmateyev.url.shortener.dto.UrlRequest;
import com.kmateyev.url.shortener.dto.UrlResponse;

public interface UrlShortenerService {

    UrlResponse createShortUrl(UrlRequest request);

    OriginalUrlResponse getOriginalUrl(String shortCode);
}

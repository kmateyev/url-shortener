package com.kmateyev.url.shortener.service.impl;

import com.kmateyev.url.shortener.config.AppConfig;
import com.kmateyev.url.shortener.dto.OriginalUrlResponse;
import com.kmateyev.url.shortener.dto.UrlRequest;
import com.kmateyev.url.shortener.dto.UrlResponse;
import com.kmateyev.url.shortener.entity.url.UrlMapping;
import com.kmateyev.url.shortener.exception.UrlNotFoundException;
import com.kmateyev.url.shortener.repository.url.UrlMappingRepository;
import com.kmateyev.url.shortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.kmateyev.url.shortener.constant.Constant.SUCCESS_MESSAGE;
import static com.kmateyev.url.shortener.constant.Constant.URL_NOT_FOUND_EXCEPTION_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlMappingRepository urlMappingRepository;
    private final AppConfig appConfig;

    @Override
    @Transactional
    public UrlResponse createShortUrl(UrlRequest request) {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        } while (urlMappingRepository.existsByShortCode(uuid));

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortCode(uuid);
        urlMapping.setOriginalUrl(request.getOriginalUrl());
        urlMappingRepository.save(urlMapping);

        UrlResponse urlResponse = new UrlResponse();
        urlResponse.setCode(201);
        urlResponse.setMessage(SUCCESS_MESSAGE);
        urlResponse.setShortCode(uuid);
        urlResponse.setShortUrl(appConfig.baseUrl() + "/" + uuid);
        urlResponse.setOriginalUrl(request.getOriginalUrl());

        return urlResponse;
    }

    @Override
    @Transactional
    public OriginalUrlResponse getOriginalUrl(String shortCode) {
        var url = urlMappingRepository.findByShortCode(shortCode);
        var urlMapping = url.orElseThrow(() -> new UrlNotFoundException(URL_NOT_FOUND_EXCEPTION_MESSAGE));
        urlMapping.setClickCount(urlMapping.getClickCount() + 1);

        var response = new OriginalUrlResponse();
        response.setOriginalUrl(urlMapping.getOriginalUrl());
        response.setCode(200);
        response.setMessage(SUCCESS_MESSAGE);

        return response;
    }
}

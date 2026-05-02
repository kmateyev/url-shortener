package com.kmateyev.url.shortener.controller;

import com.kmateyev.url.shortener.dto.OriginalUrlResponse;
import com.kmateyev.url.shortener.dto.UrlRequest;
import com.kmateyev.url.shortener.dto.UrlResponse;
import com.kmateyev.url.shortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final UrlShortenerService urlShortenerService;

    @PostMapping("/urls")
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody @Valid UrlRequest url) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(urlShortenerService.createShortUrl(url));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortCode).getOriginalUrl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }
}

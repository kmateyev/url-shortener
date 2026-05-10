package com.kmateyev.url.shortener.entity.url;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "url_mappings")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "short_code",  nullable = false)
    private String shortCode;

    @Column(name = "created_at",  nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "click_count",  nullable = false)
    private Long clickCount = 0L;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

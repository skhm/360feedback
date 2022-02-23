package com.example.feedback.controller;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/cachedemo")
public class FeedbackController {

    @GetMapping(value = "/cache", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok().
                cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body("Hello World");
    }
}

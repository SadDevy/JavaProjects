package com.translatorapi.controllers;

import com.translatorapi.models.translateobjects.TranslateObject;
import com.translatorapi.services.TranslateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class TranslateRequestController {
    @Autowired
    private TranslateRequestService translateRequestService;

    @PostMapping("/translate")
    public ResponseEntity translate(@RequestBody TranslateObject toTranslate)
            throws UnknownHostException {
        return ResponseEntity.ok(translateRequestService.translateString(toTranslate));
    }
}

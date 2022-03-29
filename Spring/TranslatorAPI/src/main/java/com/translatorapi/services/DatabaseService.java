package com.translatorapi.services;

import com.translatorapi.models.TranslateRequest;
import com.translatorapi.models.WordTranslate;
import com.translatorapi.repositories.TranslateRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@Service
public class DatabaseService {
    @Autowired
    private TranslateRequestRepository translateRequestRepository;

    public void addTranslateRequest(
            String language,
            String sourceText,
            String translatedText,
            List<WordTranslate> wordsTranslate
    ) throws UnknownHostException {
        if (!StringUtils.hasText(language))
            throw new IllegalArgumentException("language");

        if (!StringUtils.hasText(sourceText))
            throw new IllegalArgumentException("sourceText");

        if (!StringUtils.hasText(translatedText))
            throw new IllegalArgumentException("translatedText");

        if (wordsTranslate == null)
            throw new IllegalArgumentException("wordsTranslate");

        String ipAddress = InetAddress.getLocalHost().toString();
        TranslateRequest requestInformation = new TranslateRequest(sourceText, translatedText, new Date(), language, ipAddress, wordsTranslate);

        translateRequestRepository.save(requestInformation);
    }
}

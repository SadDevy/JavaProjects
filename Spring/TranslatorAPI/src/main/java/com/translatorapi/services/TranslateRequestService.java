package com.translatorapi.services;

import com.translatorapi.models.WordTranslate;
import com.translatorapi.models.translateobjects.TranslateApiObject;
import com.translatorapi.models.translateobjects.TranslateObject;
import com.translatorapi.models.translateobjects.TranslatedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TranslateRequestService {
    @Autowired
    private DatabaseService databaseService;

    private static final String TRANSLATE_API_URL = "https://libretranslate.de/translate";

    private final RestTemplate restTemplate = new RestTemplate();

    public TranslatedObject translateString(TranslateObject toTranslate)
            throws UnknownHostException {
        final String sourceLanguage = getSourceLanguage(toTranslate.getLanguage());
        final String targetLanguage = getTargetLanguage(toTranslate.getLanguage());
        final String sourceString = toTranslate.getToTranslate();

        TranslatedObject result = translateWords(sourceLanguage, targetLanguage, sourceString);

        addRequestToDb(sourceString, result.getTranslatedText(), toTranslate.getLanguage());
        return result;
    }

    private void addRequestToDb(String sourceText, String translatedText, String language)
            throws UnknownHostException {
        List<WordTranslate> wordsTranslate = getWordsTranslate(sourceText, translatedText);
        databaseService.addTranslateRequest(sourceText, translatedText, language, wordsTranslate);
    }

    private List<WordTranslate> getWordsTranslate(String sourceText, String translatedText) {
        String[] sourceWords = getWords(sourceText);
        String[] translatedWords = getWords(translatedText);

        List<WordTranslate> result = new ArrayList<>();
        for (int i = 0; i < sourceWords.length; i++) {
            WordTranslate wordTranslate = new WordTranslate(sourceWords[i], translatedWords[i]);
            result.add(wordTranslate);
        }

        return result;
    }

    private TranslatedObject translateWords(
            String sourceLanguage,
            String targetLanguage,
            String words
    ) {
        if (!StringUtils.hasText(sourceLanguage))
            throw new IllegalArgumentException("sourceLanguage");

        if (!StringUtils.hasText(targetLanguage))
            throw new IllegalArgumentException("targetLanguage");

        if (!StringUtils.hasText(words))
            throw new IllegalArgumentException("words");

        String[] sourceWords = getWords(words);
        List<String> translatedWords = translateWords(sourceLanguage, targetLanguage, sourceWords);
        String translatedText = translatedWords.stream()
                .collect(Collectors.joining(" "));

        return new TranslatedObject(sourceLanguage, targetLanguage, translatedText);
    }

    private List<String> translateWords(String sourceLanguage, String targetLanguage, String[] words) {
        if (words == null || words.length == 0)
            throw new IllegalArgumentException("words");

        if (!StringUtils.hasText(sourceLanguage))
            throw new IllegalArgumentException("sourceLanguage");

        if (!StringUtils.hasText(targetLanguage))
            throw new IllegalArgumentException("targetLanguage");

        List<String> result = new ArrayList<>();
        for (String word : words)
            result.add(translateWord(sourceLanguage, targetLanguage, word));

        return result;
    }

    private String translateWord(String sourceLanguage, String targetLanguage, String word) {
        if (!StringUtils.hasText(sourceLanguage))
            throw new IllegalArgumentException("sourceLanguage");

        if (!StringUtils.hasText(targetLanguage))
            throw new IllegalArgumentException("targetLanguage");

        if (!StringUtils.hasText(word))
            throw new IllegalArgumentException("word");

        TranslateApiObject requestObject = new TranslateApiObject(word, sourceLanguage, targetLanguage);
        return restTemplate.postForObject(TRANSLATE_API_URL, requestObject, TranslatedObject.class)
                .getTranslatedText();
    }

    private String[] getWords(String source) {
        if (!StringUtils.hasText(source))
            throw new IllegalArgumentException("source");

        final String splitRegex = "[, .?:;!]+";
        Object[] result = Stream.of(
                source.split(splitRegex)
        ).toArray();

        return Arrays.copyOf(result, result.length, String[].class);
    }

    private String getSourceLanguage(String language) {
        if (!StringUtils.hasText(language))
            throw new IllegalArgumentException("language");

        final int sourceIndex = 0;
        final String splitRegex = "-";

        String[] splitLanguage = language.split(splitRegex);
        return splitLanguage[sourceIndex];
    }

    private String getTargetLanguage(String language) {
        if (!StringUtils.hasText(language))
            throw new IllegalArgumentException("language");

        final int targetIndex = 1;
        final String splitRegex = "-";

        String[] splitLanguage = language.split(splitRegex);
        return splitLanguage[targetIndex];
    }
}

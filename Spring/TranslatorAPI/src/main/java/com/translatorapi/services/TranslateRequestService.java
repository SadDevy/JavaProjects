package com.translatorapi.services;

import com.translatorapi.models.WordTranslate;
import com.translatorapi.models.translateobjects.TranslateApiObject;
import com.translatorapi.models.translateobjects.TranslateObject;
import com.translatorapi.models.translateobjects.TranslatedObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TranslateRequestService {
    @Autowired
    private DatabaseService databaseService;

    private static final String TRANSLATE_API_URL = "https://libretranslate.de/translate";

    private static final int THREADS_COUNT = 10;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(THREADS_COUNT);
    private static final Logger logger = LoggerFactory.getLogger(TranslateRequestService.class);

    public TranslatedObject translateString(TranslateObject toTranslate)
            throws UnknownHostException, ExecutionException, InterruptedException {
        logger.trace("Started translateString method.");

        if (toTranslate == null)
            throw new IllegalArgumentException("toTranslate");

        final String sourceLanguage = getSourceLanguage(toTranslate.getLanguage());
        final String targetLanguage = getTargetLanguage(toTranslate.getLanguage());
        final String sourceString = toTranslate.getToTranslate();

        TranslatedObject result = translateWords(sourceLanguage, targetLanguage, sourceString);

        logger.debug("Add request information to database.");
        addRequestToDb(sourceString, result.getTranslatedText(), toTranslate.getLanguage());
        logger.trace("Finished translateString method.");
        return result;
    }

    private void addRequestToDb(String sourceText, String translatedText, String language)
            throws UnknownHostException {
        logger.trace("Started addRequestToDb method.");

        if (!StringUtils.hasText(sourceText))
            throw new IllegalArgumentException("sourceText");

        if (!StringUtils.hasText(translatedText))
            throw new IllegalArgumentException("translatedText");

        if (!StringUtils.hasText(language))
            throw new IllegalArgumentException("language");

        logger.debug("Add translate request.");
        List<WordTranslate> wordsTranslate = getWordsTranslate(sourceText, translatedText);
        databaseService.addTranslateRequest(sourceText, translatedText, language, wordsTranslate);

        logger.trace("Finished addRequestToDb method.");
    }

    private List<WordTranslate> getWordsTranslate(String sourceText, String translatedText) {
        logger.trace("Started getWordsTranslate method.");

        if (!StringUtils.hasText(sourceText))
            throw new IllegalArgumentException("sourceText");

        if (!StringUtils.hasText(translatedText))
            throw new IllegalArgumentException("translatedText");

        String[] sourceWords = getWords(sourceText);
        String[] translatedWords = getWords(translatedText);

        List<WordTranslate> result = new ArrayList<>();
        for (int i = 0; i < sourceWords.length; i++) {
            WordTranslate wordTranslate = new WordTranslate(sourceWords[i], translatedWords[i]);
            result.add(wordTranslate);
        }

        logger.trace("Finished getWordsTranslate method.");
        return result;
    }

    private TranslatedObject translateWords(
            String sourceLanguage,
            String targetLanguage,
            String words
    ) throws ExecutionException, InterruptedException {
        logger.trace("Started translateWords method.");

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

        logger.trace("Finished translateWords method.");
        return new TranslatedObject(sourceLanguage, targetLanguage, translatedText);
    }

    private List<String> translateWords(String sourceLanguage, String targetLanguage, String[] words)
            throws InterruptedException, ExecutionException {
        logger.trace("Started translateWords method.");

        if (words == null || words.length == 0)
            throw new IllegalArgumentException("words");

        if (!StringUtils.hasText(sourceLanguage))
            throw new IllegalArgumentException("sourceLanguage");

        if (!StringUtils.hasText(targetLanguage))
            throw new IllegalArgumentException("targetLanguage");

        logger.trace("Finished translateWords method.");
        return translateWordsParallel(sourceLanguage, targetLanguage, words);
    }

    private List<String> translateWordsParallel(String sourceLanguage, String targetLanguage, String[] words)
            throws ExecutionException, InterruptedException {
        logger.trace("Started translateWordsParallel method.");

        if (words == null || words.length == 0)
            throw new IllegalArgumentException("words");

        if (!StringUtils.hasText(sourceLanguage))
            throw new IllegalArgumentException("sourceLanguage");

        if (!StringUtils.hasText(targetLanguage))
            throw new IllegalArgumentException("targetLanguage");

        List<Callable<String>> translateWordTasks = new ArrayList<>();
        for (String word : words)
            translateWordTasks.add(() ->
                    translateWord(sourceLanguage, targetLanguage, word));

        logger.trace("Finished translateWordsParallel method.");
        return getTasksResult(translateWordTasks);
    }

    private List<String> getTasksResult(List<Callable<String>> tasks)
            throws InterruptedException, ExecutionException {
        logger.trace("Started getTasksResult method.");

        List<String> result = new ArrayList<>();
        for (Future<String> taskResult : threadPool.invokeAll(tasks))
            result.add(taskResult.get());

        logger.trace("Finished getTasksResult method.");
        return result;
    }

    private String translateWord(String sourceLanguage, String targetLanguage, String word) {
        logger.trace("Started translateWord method.");

        if (!StringUtils.hasText(sourceLanguage))
            throw new IllegalArgumentException("sourceLanguage");

        if (!StringUtils.hasText(targetLanguage))
            throw new IllegalArgumentException("targetLanguage");

        if (!StringUtils.hasText(word))
            throw new IllegalArgumentException("word");

        logger.trace("Finished translateWord method.");
        TranslateApiObject requestObject = new TranslateApiObject(word, sourceLanguage, targetLanguage);
        return restTemplate.postForObject(TRANSLATE_API_URL, requestObject, TranslatedObject.class)
                .getTranslatedText();
    }

    private String[] getWords(String source) {
        logger.trace("Started getWords method.");

        if (!StringUtils.hasText(source))
            throw new IllegalArgumentException("source");

        final String splitRegex = "[, .?:;!]+";
        Object[] result = Stream.of(
                source.split(splitRegex)
        ).toArray();

        logger.trace("Finished getWords method.");
        return Arrays.copyOf(result, result.length, String[].class);
    }

    private String getSourceLanguage(String language) {
        logger.trace("Started getSourceLanguage method.");

        if (!StringUtils.hasText(language))
            throw new IllegalArgumentException("language");

        final int sourceIndex = 0;
        final String splitRegex = "-";

        logger.trace("Finished getSourceLanguage method.");
        String[] splitLanguage = language.split(splitRegex);
        return splitLanguage[sourceIndex];
    }

    private String getTargetLanguage(String language) {
        logger.trace("Started getTargetLanguage method.");

        if (!StringUtils.hasText(language))
            throw new IllegalArgumentException("language");

        final int targetIndex = 1;
        final String splitRegex = "-";

        logger.trace("Finished getTargetLanguage method.");
        String[] splitLanguage = language.split(splitRegex);
        return splitLanguage[targetIndex];
    }
}

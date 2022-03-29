package com.translatorapi.models.translateobjects;

public class TranslatedObject {
    private String sourceLanguage;
    private String targetLanguage;
    private String translatedText;

    public TranslatedObject() {}

    public TranslatedObject(
            String sourceLanguage,
            String targetLanguage,
            String text
    ) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.translatedText = text;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}

package com.translatorapi.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "translate_requests")
public class TranslateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String sourceText;
    private String translatedText;
    private Date requestDate;
    private String language;
    private String ipAddress;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "translate_request_id")
    private List<WordTranslate> wordsTranslate;

    public TranslateRequest() {}

    public TranslateRequest(
            String sourceText,
            String translatedText,
            Date requestDate,
            String language,
            String ipAddress
    ) {
        this.sourceText = sourceText;
        this.translatedText = translatedText;
        this.requestDate = requestDate;
        this.language = language;
        this.ipAddress = ipAddress;
    }

    public TranslateRequest(
            String sourceText,
            String translatedText,
            Date requestDate,
            String language,
            String ipAddress,
            List<WordTranslate> wordsTranslate
    ) {
        this(sourceText, translatedText, requestDate, language, ipAddress);
        this.wordsTranslate = wordsTranslate;
    }

    public int getId() {
        return id;
    }

    public String getSourceText() {
        return sourceText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public String getLanguage() {
        return language;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public List<WordTranslate> getWordsTranslate() {
        return wordsTranslate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    @Override
    public String toString() {
        return "TranslateRequest{" +
                "id=" + id +
                ", sourceText='" + sourceText + '\'' +
                ", translatedText='" + translatedText + '\'' +
                ", requestDate=" + requestDate +
                ", language='" + language + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", wordsTranslate=" + wordsTranslate +
                '}';
    }
}

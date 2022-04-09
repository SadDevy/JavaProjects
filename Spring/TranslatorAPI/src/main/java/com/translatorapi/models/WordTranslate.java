package com.translatorapi.models;

import javax.persistence.*;

@Entity
@Table(name = "words_translate")
public class WordTranslate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String source;
    private String translated;

    public WordTranslate() {}

    public WordTranslate(
            String source,
            String translated
    ) {
        this.source = source;
        this.translated = translated;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getTranslated() {
        return translated;
    }

    @Override
    public String toString() {
        return "WordTranslate{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", translated='" + translated + '\'' +
                '}';
    }
}

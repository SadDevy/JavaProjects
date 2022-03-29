package com.translatorapi.models.translateobjects;

public class TranslateApiObject {
    private String q;
    private String source;
    private String target;

    public TranslateApiObject(String q, String source, String target) {
        this.q = q;
        this.source = source;
        this.target = target;
    }

    public String getQ() {
        return q;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}

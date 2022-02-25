package Entities;

import java.io.Serializable;

public class AnswerVariant
        implements Serializable {
    private transient int id;
    public int getId() {
        return id;
    }

    private int questionId;
    public int getQuestionId() {
        return questionId;
    }

    private String description;
    public String getDescription() {
        return description;
    }

    private boolean isCorrected;
    public boolean isCorrected() {
        return isCorrected;
    }

    public AnswerVariant(
            int id,
            int questionId,
            String description,
            boolean isCorrected
    ) {
        this.id = id;
        this.questionId = questionId;
        this.description = description;
        this.isCorrected = isCorrected;
    }
}

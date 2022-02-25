package Entities;

import java.io.Serializable;
import java.util.List;

public class Question
        implements Serializable {
    private transient int id;
    public int getId() {
        return id;
    }

    private int testId;
    public int getTestId() {
        return testId;
    }

    private String description;
    public String getDescription() {
        return description;
    }

    private List<AnswerVariant> answerVariants;
    public List<AnswerVariant> getAnswerVariants() {
        return answerVariants;
    }

    public void setAnswerVariants(List<AnswerVariant> answerVariants) {
        this.answerVariants = answerVariants;
    }

    public Question(
            int id,
            int testId,
            String description
    ) {
        this.id = id;
        this.testId = testId;
        this.description = description;
    }
}

package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"AnswerVariants\"")
public class AnswerVariant
        implements Serializable {
    @Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "\"QuestionId\"")
//    private int questionId;
    @Column(name = "\"Description\"")
    private String description;
    @Column(name = "\"IsCorrected\"")
    private boolean isCorrected;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "\"QuestionId\"")
//    private Question question;

    public AnswerVariant() {
    }

    public AnswerVariant(
            int questionId,
            String description,
            boolean isCorrected
    ) {
//        this.questionId = questionId;
        this.description = description;
        this.isCorrected = isCorrected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(int questionId) {
//        this.questionId = questionId;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrected() {
        return isCorrected;
    }

    public void setCorrected(boolean corrected) {
        isCorrected = corrected;
    }

//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }
}

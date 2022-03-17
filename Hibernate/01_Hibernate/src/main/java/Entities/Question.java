package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "\"Questions\"")
public class Question
        implements Serializable {
    @Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @Column(name = "\"TestId\"")
//    private int testId;
    @Column(name = "\"Description\"")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "\"QuestionId\"")
    private List<AnswerVariant> answerVariants;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "\"TestId\"")
//    private Test test;

    public Question() {
    }

    public Question(
            int testId,
            String description
    ) {
//        this.testId = testId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getTestId() {
//        return testId;
//    }
//
//    public void setTestId(int testId) {
//        this.testId = testId;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AnswerVariant> getAnswerVariants() {
        return answerVariants;
    }

    public void setAnswerVariants(List<AnswerVariant> answerVariants) {
        this.answerVariants = answerVariants;
    }

//    public Test getTest() {
//        return test;
//    }
//
//    public void setTest(Test test) {
//        this.test = test;
//    }
}

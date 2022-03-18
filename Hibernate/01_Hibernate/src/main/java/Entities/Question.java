package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "\"Questions\"")
public class Question
        implements Serializable {
    @Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "\"Description\"")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "\"QuestionId\"")
    private List<AnswerVariant> answerVariants;

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        Question question = (Question) o;
        return id == question.id &&
                Objects.equals(description, question.description) &&
                Objects.equals(answerVariants, question.answerVariants);
    }

    private boolean isType(Object o) {
        try {
            Question result = (Question) o;

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, answerVariants);
    }
}

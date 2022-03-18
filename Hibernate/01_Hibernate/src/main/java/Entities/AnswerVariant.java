package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "\"AnswerVariants\"")
public class AnswerVariant
        implements Serializable {
    @Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "\"Description\"")
    private String description;
    @Column(name = "\"IsCorrected\"")
    private boolean isCorrected;

    public AnswerVariant() {
    }

    public AnswerVariant(
            String description,
            boolean isCorrected
    ) {
        this.description = description;
        this.isCorrected = isCorrected;
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

    public boolean isCorrected() {
        return isCorrected;
    }

    public void setCorrected(boolean corrected) {
        isCorrected = corrected;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        AnswerVariant that = (AnswerVariant) o;
        return id == that.id &&
                isCorrected == that.isCorrected &&
                Objects.equals(description, that.description);
    }

    private boolean isType(Object o) {
        try {
            AnswerVariant result = (AnswerVariant) o;

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isCorrected);
    }
}

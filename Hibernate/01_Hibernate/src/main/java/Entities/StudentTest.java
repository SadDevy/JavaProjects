package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "\"Tests\"")
public class StudentTest
        implements Serializable {
    @Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "\"Name\"")
    private String name;
    @Column(name = "\"Theory\"")
    private String theory;
    @Column(name = "\"TheoryUrl\"")
    private String theoryUrl;
    @Column(name = "\"TheoryIsShown\"")
    private boolean theoryIsShown;
    @Column(name = "\"QuestionsCount\"")
    private int questionsCount;
    @Column(name = "\"RightCount\"")
    private int rightCount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "\"TestId\"")
    private List<Question> questions;

    public StudentTest() {
    }

    public StudentTest(
            String name,
            String theory,
            String theoryUrl,
            boolean theoryIsShown,
            int questionsCount,
            int rightCount
    ) {
        this.name = name;
        this.theory = theory;
        this.theoryUrl = theoryUrl;
        this.theoryIsShown = theoryIsShown;
        this.questionsCount = questionsCount;
        this.rightCount = rightCount;
    }

    public StudentTest(
            int id,
            String name,
            String theory,
            String theoryUrl,
            boolean theoryIsShown,
            int questionsCount,
            int rightCount
    ) {
        this.id = id;
        this.name = name;
        this.theory = theory;
        this.theoryUrl = theoryUrl;
        this.theoryIsShown = theoryIsShown;
        this.questionsCount = questionsCount;
        this.rightCount = rightCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheory() {
        return theory;
    }

    public void setTheory(String theory) {
        this.theory = theory;
    }

    public String getTheoryUrl() {
        return theoryUrl;
    }

    public void setTheoryUrl(String theoryUrl) {
        this.theoryUrl = theoryUrl;
    }

    public boolean isTheoryIsShown() {
        return theoryIsShown;
    }

    public void setTheoryIsShown(boolean theoryIsShown) {
        this.theoryIsShown = theoryIsShown;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        StudentTest test = (StudentTest) o;
        return id == test.id &&
                theoryIsShown == test.theoryIsShown &&
                questionsCount == test.questionsCount &&
                rightCount == test.rightCount &&
                Objects.equals(name, test.name) &&
                Objects.equals(theory, test.theory) &&
                Objects.equals(theoryUrl, test.theoryUrl);
    }

    private boolean isType(Object o) {
        try {
            StudentTest result = (StudentTest) o;

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, theory, theoryUrl, theoryIsShown, questionsCount, rightCount, questions);
    }
}

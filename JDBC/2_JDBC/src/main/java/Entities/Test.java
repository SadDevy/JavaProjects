package Entities;

import java.io.Serializable;
import java.util.List;

public class Test
        implements Serializable {
    private transient int id;
    public int getId() {
        return id;
    }

    private String name;
    public String getName() {
        return name;
    }

    private String theory;
    public String getTheory() {
        return theory;
    }

    private String theoryUrl;
    public String getTheoryUrl() {
        return theoryUrl;
    }

    private byte[] image;
    public byte[] getImage() {
        return image;
    }

    private boolean theoryIsShown;
    public boolean theoryIsShown() {
        return theoryIsShown;
    }

    private int testTime;
    public int getTestTime() {
        return testTime;
    }

    private int questionsCount;
    public int getQuestionsCount() {
        return questionsCount;
    }

    private int rightCount;
    public int getRightCount() {
        return rightCount;
    }

    private List<Question> questions;
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Test(
            int id,
            String name,
            String theory,
            String theoryUrl,
            byte[] image,
            boolean theoryIsShown,
            int testTime,
            int questionsCount,
            int rightCount
    ) {
        this.id = id;
        this.name = name;
        this.theory = theory;
        this.theoryUrl = theoryUrl;
        this.image = image;
        this.theoryIsShown = theoryIsShown;
        this.testTime = testTime;
        this. questionsCount = questionsCount;
        this.rightCount = rightCount;
    }
}

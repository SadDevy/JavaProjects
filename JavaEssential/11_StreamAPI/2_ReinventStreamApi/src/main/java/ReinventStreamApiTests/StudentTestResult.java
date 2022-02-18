package ReinventStreamApiTests;

import java.util.Date;
import java.util.Objects;

public class StudentTestResult
        implements Comparable<StudentTestResult> {
    private String name;

    public String getName() {
        return name;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    private String testSubject;

    public String getTestSubject() {
        return testSubject;
    }

    private int testScore;

    public int getTestScore() {
        return testScore;
    }

    public Date date;

    public Date getDate() {
        return date;
    }

    public StudentTestResult(String name,
                             String lastName,
                             String testSubject,
                             int testScore,
                             Date date) {
        if (name == null)
            throw new IllegalArgumentException("firstName");

        if (lastName == null)
            throw new IllegalArgumentException("lastName");

        if (testSubject == null)
            throw new IllegalArgumentException("testSubject");

        if (date == null)
            throw new IllegalArgumentException("date");

        this.name = name;
        this.lastName = lastName;
        this.testSubject = testSubject;
        this.testScore = testScore;
        this.date = date;
    }

    @Override
    public int compareTo(StudentTestResult o) {
        if (o == null)
            return 1;

        int result = Integer.compare(this.testScore, o.getTestScore());
        if (result != 0)
            return result;

        result = testSubject.compareTo(o.getTestSubject());
        if (result != 0)
            return result;

        result = name.compareTo(o.getName());
        if (result != 0)
            return result;

        result = lastName.compareTo(o.getLastName());
        if (result != 0)
            return result;

        return date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        StudentTestResult result = (StudentTestResult) o;
        return compareTo(result) == 0;
    }

    private boolean isType(Object obj) {
        try {
            StudentTestResult __ = (StudentTestResult) obj;
            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, testSubject, testScore, date);
    }

    @Override
    public String toString() {
        return String.format("%s %s / %s [%d]", name, lastName, testSubject, testScore);
    }
}

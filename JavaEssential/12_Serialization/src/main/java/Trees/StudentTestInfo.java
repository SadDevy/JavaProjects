package Trees;

import java.io.Serializable;
import java.util.Date;

public class StudentTestInfo
        implements Comparable<StudentTestInfo>, Serializable {
    private String name;

    public String getName() {
        return name;
    }

    private String surname;

    public String getSurname() {
        return surname;
    }

    private String testName;

    public String getTestName() {
        return testName;
    }

    private Date passingDate;

    public Date getPassingDate() {
        return passingDate;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public StudentTestInfo(String name, String surname, String testName, Date passingDate, int score) {
        this.name = name;
        this.surname = surname;
        this.testName = testName;
        this.passingDate = passingDate;
        this.score = score;
    }

    public int compareTo(StudentTestInfo a) {
        int result = Integer.compare(score, a.score);
        if (result != 0) return result;

        result = name.compareTo(a.name);
        if (result != 0) return result;

        result = surname.compareTo(a.surname);
        if (result != 0) return result;

        result = testName.compareTo(a.testName);
        if (result != 0) return  result;

        return passingDate.compareTo(a.passingDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        StudentTestInfo a = (StudentTestInfo) o;
        return equals(a);
    }

    private boolean equals(StudentTestInfo a) {
        if (a == null)
            return false;

        return compareTo(a) == 0;
    }

    private boolean isType(Object o) {
        try {
            StudentTestInfo __ = (StudentTestInfo) o;

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ surname.hashCode() ^ testName.hashCode()
                ^ passingDate.hashCode() ^ Integer.hashCode(score);
    }
}
package Trees;

import java.util.Date;

public class StudentTestInfo implements Comparable<StudentTestInfo> {
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
}

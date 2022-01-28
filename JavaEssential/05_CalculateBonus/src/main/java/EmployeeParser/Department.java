package EmployeeParser;

public enum Department {
    NotDefined(-1),
    RD(0),
    QA(1),
    Support(2),
    Sales(3),
    Marketing(4);

    private int code;
    public int getCode() {
        return code;
    }

    Department(int code) {
        this.code = code;
    }
}

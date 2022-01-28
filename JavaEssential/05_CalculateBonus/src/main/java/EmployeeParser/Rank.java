package EmployeeParser;

public enum Rank {
    NotDefined(-1),
    Employee(0),
    Lead(1),
    Manager(2),
    Director(3);

    private int code;

    public int getCode() {
        return code;
    }

    Rank(int code) {
        this.code = code;
    }
}

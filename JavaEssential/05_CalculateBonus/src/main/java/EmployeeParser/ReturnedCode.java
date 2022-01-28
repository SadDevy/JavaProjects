package EmployeeParser;

public enum ReturnedCode {
    Success(0),
    ErrorNotRequiredParameter(-1),
    ErrorInvalidRank(-4),
    ErrorRankNotExists(-5),
    ErrorInvalidDepartment(-6),
    ErrorDepartmentNotExists(-7),
    ErrorInvalidSalary(-8),
    ErrorInvalidSalaryPercent(-9),
    ErrorInvalidCoefficient(-10),
    ErrorInvalidFormatRequiredParameter(-11),
    ErrorEmptySurname(-12),
    ErrorEmptyParameters(-13),
    ErrorInvalidEmployeeCodeFormat(-14);

    private int code;
    public int getCode() {
        return code;
    }

    ReturnedCode(int code) {
        this.code = code;
    }
}

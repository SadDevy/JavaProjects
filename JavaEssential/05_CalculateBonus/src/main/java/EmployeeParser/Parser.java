package EmployeeParser;

import java.util.concurrent.atomic.AtomicReference;

public class Parser {
    private static final int requiredParameterPartsCount = 2;

    private static final char lineSeparator = ',';

    private static final int requiredParameterIndex = 0;
    private static final int salaryIndex = 1;
    private static final int salaryPercentIndex = 2;
    private static final int coefficientIndex = 3;

    private static final int employeeCodeIndex = 0;
    private static final int surnameIndex = 1;
    private static final int departmentIndex = 0;
    private static final int rankIndex= 1;

    private static final int employeeCodeLength = 2;

    public static ReturnedCode tryParseRank(char code, AtomicReference<Rank> rank) {
        rank.set(Rank.NotDefined);

        if (!hasRank(code))
            return ReturnedCode.ErrorInvalidRank;

        rank.set(Rank.valueOf(String.valueOf(code)));
        return ReturnedCode.Success;
    }

    private static boolean hasRank(char code) {
        try {
            for (Rank rank : Rank.values()) {
                if (rank.getCode() == (int)code)
                    return true;
            }

            return false;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static ReturnedCode tryParseDepartment(char code, AtomicReference<Department> department) {
        department.set(Department.NotDefined);

        if (!hasDepartment(code))
            return ReturnedCode.ErrorInvalidDepartment;

        department.set(Department.valueOf(String.valueOf(code)));
        return ReturnedCode.Success;
    }

    private static boolean hasDepartment(char code) {
        try {
            for (Department department : Department.values()) {
                if (department.getCode() == (int)code)
                    return true;
            }

            return false;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static ReturnedCode tryParseRequiredParameter(String value, AtomicReference<Employee> employee) {
        employee.set(null);

        String[] valueParts = value.split(String.valueOf(lineSeparator));
        if (valueParts.length != requiredParameterPartsCount)
            return ReturnedCode.ErrorInvalidFormatRequiredParameter;

        String employeeCode = valueParts[employeeCodeIndex];
        if (employeeCode.length() != employeeCodeLength)
            return ReturnedCode.ErrorInvalidEmployeeCodeFormat;

        String surname = valueParts[surnameIndex];
        if (surname == null || surname == "")
            return ReturnedCode.ErrorEmptySurname;

        AtomicReference<Department> departmentReference = new AtomicReference<>();
        ReturnedCode departmentReturnedCode = tryParseDepartment(employeeCode.charAt(departmentIndex), departmentReference);
        if (departmentReturnedCode != ReturnedCode.Success)
            return departmentReturnedCode;

        AtomicReference<Rank> rankReference = new AtomicReference<>();
        ReturnedCode rankReturnedCode = tryParseRank(employeeCode.charAt(rankIndex), rankReference);
        if (rankReturnedCode != ReturnedCode.Success)
            return rankReturnedCode;

        employee.set(new Employee(surname, rankReference.get(), departmentReference.get()));
        return ReturnedCode.Success;
    }

    public static ReturnedCode tryParseSalary(String value, AtomicReference<Integer> salary) {
        salary.set(0);

        AtomicReference<Integer> parsedSalary = new AtomicReference<>();
        if (!tryParseInteger(value, parsedSalary) || parsedSalary.get() < 0)
            return ReturnedCode.ErrorInvalidSalary;

        salary.set(parsedSalary.get());
        return ReturnedCode.Success;
    }

    private static boolean tryParseInteger(String value, AtomicReference<Integer> salary) {
        try {
            Integer number = Integer.parseInt(value);
            salary.set(number);

            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static ReturnedCode tryParseSalaryPercent(String value, AtomicReference<Byte> salaryPercent) {
        salaryPercent.set((byte)0);

        AtomicReference<Byte> parsedSalaryPercent = new AtomicReference<>();
        if (!tryParseByte(value, parsedSalaryPercent) || parsedSalaryPercent.get() < 0)
            return ReturnedCode.ErrorInvalidSalaryPercent;

        salaryPercent.set(parsedSalaryPercent.get());
        return ReturnedCode.Success;
    }

    private static boolean tryParseByte(String value, AtomicReference<Byte> result) {
        try {
            Byte number = Byte.parseByte(value);
            result.set(number);

            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static ReturnedCode tryParseCoefficient(String value, AtomicReference<Double> coefficient) {
        coefficient.set(0.0);

        AtomicReference<Double> parsedCoefficient = new AtomicReference<>();
        if (!tryParseDouble(value, parsedCoefficient) || parsedCoefficient.get() < 0)
            return ReturnedCode.ErrorInvalidCoefficient;

        coefficient.set(parsedCoefficient.get());
        return ReturnedCode.Success;
    }

    private static boolean tryParseDouble(String value, AtomicReference<Double> result) {
        try {
            Double number = Double.parseDouble(value);
            result.set(number);

            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static ReturnedCode tryParseEmployee(String[] values, AtomicReference<Employee> employee) {
        employee.set(null);

        if (values == null)
            return ReturnedCode.ErrorEmptyParameters;

        if (values.length == requiredParameterIndex)
            return ReturnedCode.ErrorNotRequiredParameter;

        ReturnedCode requiredParameterReturnedCode = tryParseRequiredParameter(values[requiredParameterIndex], employee);
        if (requiredParameterReturnedCode != ReturnedCode.ErrorInvalidFormatRequiredParameter.Success)
            return requiredParameterReturnedCode;

        if (values.length > salaryIndex) {
            AtomicReference<Integer> salaryReference = new AtomicReference<>();
            ReturnedCode salaryReturnedCode = tryParseSalary(values[salaryIndex], salaryReference);
            if (salaryReturnedCode != ReturnedCode.Success)
                return salaryReturnedCode;

            employee.get().setSalary(salaryReference.get());
        }

        if (values.length > salaryPercentIndex) {
            AtomicReference<Byte> salaryPercentReference = new AtomicReference<>();
            ReturnedCode salaryPercentReturnedCode = tryParseSalaryPercent(values[salaryPercentIndex], salaryPercentReference);
            if (salaryPercentReturnedCode != ReturnedCode.Success)
                return salaryPercentReturnedCode;

            employee.get().setSalaryPercent(salaryPercentReference.get());
        }

        if (values.length > coefficientIndex) {
            AtomicReference<Double> coefficientReference = new AtomicReference<>();
            ReturnedCode coefficientReturnedCode = tryParseCoefficient(values[coefficientIndex], coefficientReference);
            if (coefficientReturnedCode != ReturnedCode.Success)
                return coefficientReturnedCode;

            employee.get().setCoefficient(coefficientReference.get());
        }

        return ReturnedCode.Success;
    }
}

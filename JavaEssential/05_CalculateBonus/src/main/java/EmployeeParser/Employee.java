package EmployeeParser;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class Employee {
    private static final byte taxBorder = 10;
    private static final byte defaultTaxRate = 13;
    private static final int defaultSalary = 500;
    private static final byte defaultSalaryPercent = 10;
    private static final double defaultCoefficient = 0.96;

    private String surname;
    public String getSurname() {
        return surname;
    }

    private Rank rank;
    public Rank getRank() {
        return rank;
    }

    private Department department;
    public Department getDepartment() {
        return department;
    }

    private int salary;
    public int getSalary() {
        return salary;
    }

    public void setSalary(int value) {
        salary = value;
    }

    private byte salaryPercent;
    public byte getSalaryPercent() {
        return salaryPercent;
    }

    public void setSalaryPercent(byte value) {
        salaryPercent = value;
    }

    private double coefficient;
    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double value) {
        coefficient = value;
    }

    public Employee(String surname, Rank rank, Department department) {
        this(surname, rank, department, defaultSalary);
    }

    public Employee(String surname, Rank rank, Department department, int salary) {
        this(surname, rank, department, defaultSalary, defaultSalaryPercent);
    }

    public Employee(String surname, Rank rank, Department department, int salary, byte salaryPercent) {
        this(surname, rank, department, salary, salaryPercent, defaultCoefficient);
    }

    public Employee(String surname, Rank rank, Department department, int salary, byte salaryPercent, double coefficient) {
        this.surname = surname;
        this.rank = rank;
        this.department = department;
        this.salary = salary;
        this.salaryPercent = salaryPercent;
        this.coefficient = coefficient;
    }

    public double calculateBonus() {
        return salary * salaryPercent / 100.0 * coefficient;
    }

    public boolean payTax(AtomicReference<Double> bonus, AtomicReference<Byte> taxRate) {
        taxRate.set(defaultTaxRate);

        if (bonus.get() < taxRate.get())
            return false;

        bonus.set(bonus.get() - (bonus.get() * taxRate.get() / 100));
        return true;
    }
}

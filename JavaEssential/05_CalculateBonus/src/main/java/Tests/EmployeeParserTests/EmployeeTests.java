package Tests.EmployeeParserTests;

import EmployeeParser.Department;
import EmployeeParser.Employee;
import EmployeeParser.Rank;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class EmployeeTests {
    @Test
    public void testConstructor_onlyRequiredParameter_success() {
        final Rank rank = Rank.Director;
        final Department department = Department.RD;
        final String surname = "Сидоров";
        final int defaultSalary = 500;
        final byte defaultSalaryPercent = 10;
        final double defaultCoefficient = 0.96;

        Employee employee = new Employee(surname, rank, department);

        Assert.assertNotNull(employee);
        Assert.assertEquals(surname, employee.getSurname());
        Assert.assertEquals(rank, employee.getRank());
        Assert.assertEquals(department, employee.getDepartment());

        Assert.assertEquals(defaultSalary, employee.getSalary(), 0);
        Assert.assertEquals(defaultSalaryPercent, employee.getSalaryPercent(), 0);
        Assert.assertEquals(defaultCoefficient, employee.getCoefficient(), 0);
    }

    @Test
    public void testConstructor_allParameters_success() {
        final Rank rank = Rank.Director;
        final Department department = Department.RD;
        final String surname = "Сидоров";
        final int salary = 300;
        final byte salaryPercent = 9;
        final double coefficient = 0.3;

        Employee employee = new Employee(surname, rank, department, salary, salaryPercent, coefficient);

        Assert.assertNotNull(employee);
        Assert.assertEquals(surname, employee.getSurname());
        Assert.assertEquals(rank, employee.getRank());
        Assert.assertEquals(department, employee.getDepartment());

        Assert.assertEquals(salary, employee.getSalary(), 0);
        Assert.assertEquals(salaryPercent, employee.getSalaryPercent(), 0);
        Assert.assertEquals(coefficient, employee.getCoefficient(), 0);
    }

    @Test
    public void testCalculateBonus_defaultSalary_success() {
        Employee employee = new Employee("Сидоров", Rank.Employee, Department.QA);
        final double expected = 48.0;

        double actual = employee.calculateBonus();

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testPayTax_bonusMoreTaxBorder_success() {
        Employee employee = new Employee("Сидоров", Rank.Employee, Department.QA, 1200, (byte)10, 0.1);
        final byte expectedTaxRate = 13;
        final double expected = 10.44;

        AtomicReference<Double> actual = new AtomicReference<>();
        actual.set(employee.calculateBonus());
        AtomicReference<Byte> actualTaxRate = new AtomicReference<>();
        boolean result = employee.payTax(actual, actualTaxRate);

        Assert.assertEquals(expectedTaxRate, actualTaxRate.get(), 0);
        Assert.assertEquals(expected, actual.get(), 0);
        Assert.assertTrue(result);
    }

    @Test
    public void testPayTax_bonusIsTaxBorder_success() {
        Employee employee = new Employee("Сидоров", Rank.Employee, Department.QA, 1000, (byte)10, 0.1);
        final byte expectedTaxRate = 13;
        final double expected = 8.7;

        AtomicReference<Double> actual = new AtomicReference<>();
        actual.set(employee.calculateBonus());
        AtomicReference<Byte> actualTaxRate = new AtomicReference<>();
        boolean result = employee.payTax(actual, actualTaxRate);

        Assert.assertEquals(expectedTaxRate, actualTaxRate.get(), 0);
        Assert.assertEquals(expected, actual.get(), 0);
        Assert.assertTrue(result);
    }

    @Test
    public void testPayTax_bonusLessTaxBorder_success() {
        Employee employee = new Employee("Сидоров", Rank.Employee, Department.QA, 300, (byte)10, 0.1);
        final byte expectedRate = 13;
        final double expected = 3.0;

        AtomicReference<Double> actual = new AtomicReference<>();
        actual.set(employee.calculateBonus());
        AtomicReference<Byte> actualTaxRate = new AtomicReference<>();
        boolean result = employee.payTax(actual, actualTaxRate);

        Assert.assertEquals(expectedRate, actualTaxRate.get(), 0);
        Assert.assertEquals(expected, actual.get(), 0);
        Assert.assertFalse(result);
    }
}

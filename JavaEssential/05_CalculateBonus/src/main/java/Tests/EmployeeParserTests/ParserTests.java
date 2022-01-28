package Tests.EmployeeParserTests;

import EmployeeParser.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class ParserTests {
    @Test
    public void testTryParseRequiredParameter_twelveSidorov_success() {
        final String line = "12,Сидоров";

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRequiredParameter(line, employeeReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertNotNull(employeeReference.get());
    }

    @Test
    public void testTryParseRequiredParameter_requiredParameterNull_failure() {
        final String line = "Сидоров";

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRequiredParameter(line, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidFormatRequiredParameter, result);
        Assert.assertNull(employeeReference.get());
    }

    @Test
    public void testTryRequiredParameter_departmentEight_failure() {
        final String line = "81,Сидоров";

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRequiredParameter(line, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorDepartmentNotExists, result);
        Assert.assertNull(employeeReference.get());
    }

    @Test
    public void testTryRequiredParameter_departmentLetter_failure() {
        final String line = "а1,Сидоров";

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRequiredParameter(line, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidDepartment, result);
        Assert.assertNull(employeeReference.get());
    }

    @Test
    public void testTryRequiredParameter_rankEight_failure() {
        final String line = "18,Сидоров";

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRequiredParameter(line, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorRankNotExists, result);
        Assert.assertNull(employeeReference.get());
    }

    @Test
    public void testTryRequiredParameter_rankLetter_failure() {
        final String line = "1а,Сидоров";

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRequiredParameter(line, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidRank, result);
        Assert.assertNull(employeeReference.get());
    }

    @Test
    public void testTryParseDepartment_one_success() {
        final char code = '1';
        Department expected = Department.QA;

        AtomicReference<Department> actual = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseDepartment(code, actual);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTryParseDepartment_letter_failure() {
        final char code = 'a';
        Department expected = Department.NotDefined;

        AtomicReference<Department> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseDepartment(code, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidDepartment, result);
        Assert.assertEquals(expected, actualReference.get());
    }

    @Test
    public void testTryParseRank_one_success() {
        final char code = '1';
        final Rank expected = Rank.Lead;

        AtomicReference<Rank> rankReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRank(code, rankReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertEquals(expected, rankReference.get());
    }

    @Test
    public void testTryParseRank_letter_failure() {
        final char code = 'a';
        final Rank expected = Rank.NotDefined;

        AtomicReference<Rank> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseRank(code, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidRank, result);
        Assert.assertEquals(expected, actualReference.get());
    }

    @Test
    public void testTryParseSalary_oneHundred_success() {
        final String line = "100";
        final int expected = 100;

        AtomicReference<Integer> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalary(line, actualReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalary_letter_failure() {
        final String line = "a";
        final int expected = 0;

        AtomicReference<Integer> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalary(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidRank, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalary_negative_failure() {
        final String line = "-10";
        final int expected = 0;

        AtomicReference<Integer> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalary(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalary, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalary_empty_failure() {
        final String line = "";
        final int expected = 0;

        AtomicReference<Integer> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalary(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalary, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalaryPercent_fifty_success() {
        final String line = "50";
        final byte expected = 50;

        AtomicReference<Byte> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalaryPercent(line, actualReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalaryPercent_letter_failure() {
        final String line = "a";
        final byte expected = 0;

        AtomicReference<Byte> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalaryPercent(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalaryPercent, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalaryPercent_negative_failure() {
        final String line = "-10";
        final byte expected = 0;

        AtomicReference<Byte> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalaryPercent(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalaryPercent, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseSalaryPercent_empty_failure() {
        final String line = "";
        final byte expected = 0;

        AtomicReference<Byte> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseSalaryPercent(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalaryPercent, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseCoefficient_zeroPointOne_success() {
        final String line = "0.1";
        final double expected = 0.1;

        AtomicReference<Double> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseCoefficient(line, actualReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseCoefficient_letter_failure() {
        final String line = "a";
        final double expected = 0;

        AtomicReference<Double> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseCoefficient(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidCoefficient, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseCoefficient_negative_failure() {
        final String line = "-0.1";
        final double expected = 0;

        AtomicReference<Double> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseCoefficient(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidCoefficient, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseCoefficient_empty_failure() {
        final String line = "";
        final double expected = 0;

        AtomicReference<Double> actualReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseCoefficient(line, actualReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidCoefficient, result);
        Assert.assertEquals(expected, actualReference.get(), 0);
    }

    @Test
    public void testTryParseEmployee_corrected_success() {
        String[] lineParts = { "12,Сидоров", "300", "7", "0.87" };

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseEmployee(lineParts, employeeReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertNotNull(employeeReference.get());
    }

    @Test
    public void testTryParseEmployee_coefficientEmpty_success() {
        String[] lineParts = { "12,Сидоров", "300", "7" };

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseEmployee(lineParts, employeeReference);

        Assert.assertEquals(ReturnedCode.Success, result);
        Assert.assertNotNull(employeeReference.get());
    }

    @Test
    public void testTryParseEmployee_requiredParameterEmpty_Failure() {
        String[] lineParts = { "300", "5", "0.9" };

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseEmployee(lineParts, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidFormatRequiredParameter, result);
        Assert.assertNull(employeeReference.get());
    }

    @Test
    public void testTryParseEmployee_coefficientLetter_failure() {
        String[] lineParts = { "12,Сидоров", "300", "7", "a" };

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseEmployee(lineParts, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidCoefficient, result);
    }

    @Test
    public void testTryParseEmployee_salaryPercentLetter_failure() {
        String[] lineParts = { "12,Сидоров", "300", "a", "0.9" };

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseEmployee(lineParts, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalaryPercent, result);
    }

    @Test
    public void testTryParseEmployee_salaryLetter_failure() {
        String[] lineParts = { "12,Сидоров", "a", "5", "0.9" };

        AtomicReference<Employee> employeeReference = new AtomicReference<>();
        ReturnedCode result = Parser.tryParseEmployee(lineParts, employeeReference);

        Assert.assertEquals(ReturnedCode.ErrorInvalidSalary, result);
    }
}


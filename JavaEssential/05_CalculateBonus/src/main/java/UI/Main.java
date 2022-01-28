package UI;

import EmployeeParser.Employee;
import EmployeeParser.Parser;
import EmployeeParser.ReturnedCode;

import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static int main(String[] args) {
        AtomicReference<Employee> employee = new AtomicReference<>();
        ReturnedCode returnedCode = Parser.tryParseEmployee(args, employee);
        if (returnedCode == ReturnedCode.Success) {
            AtomicReference<Double> bonus = new AtomicReference<>();
            bonus.set(employee.get().calculateBonus());

            AtomicReference<Byte> taxRate = new AtomicReference<>();
            boolean isTaxed = employee.get().payTax(bonus, taxRate);

            showResult(employee.get(), bonus.get(), taxRate.get(), isTaxed);
        }

        return returnedCode.getCode();
    }

    private static void showResult(Employee employee, double bonus, double taxRate, boolean isTaxed) {
        String employeeLine = String.format("Сотрудник: %s.", employee.getSurname().toUpperCase());
        System.out.println(employeeLine);

        String departmentLine = String.format("Отдел: %s.", employee.getDepartment());
        System.out.println(departmentLine);

        String rankLine = String.format("Должность: %s.", employee.getRank());
        System.out.println(rankLine);

        String paymentMessageFormat = isTaxed ? "Включая налог: %f%." : "Не включая налог: %f%.";
        String paymentMessage = String.format(paymentMessageFormat, taxRate);
        System.out.println(paymentMessage);

        String primeLine = String.format("Премия: %f.", bonus);
        System.out.println(primeLine);
    }
}

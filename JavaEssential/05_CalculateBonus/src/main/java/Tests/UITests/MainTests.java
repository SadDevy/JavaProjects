package Tests.UITests;

import UI.Main;
import org.junit.Assert;
import org.junit.Test;

public class MainTests {
    @Test
    public void testMain_optionalParameters_success() {
        String[] values = { "12,Сидоров" };
        final String expected = """
Сотрудник: СИДОРОВ.
Отдел: QA.
Должность: Manager.
Включая налог: 13%.
Премия: 41,76.
""";

        try (ConsoleOutput actual = new ConsoleOutput()) {
            Main.main(values);

            Assert.assertEquals(expected, actual.getOutput());
        }
    }

    @Test
    public void testMain_salaryTen_success() {
        String[] values = { "12,Сидоров", "10" };
        final String expected = """
Сотрудник: СИДОРОВ.
Отдел: QA.
Должность: Manager.
Не включая налог: 13%.
Премия: 0,96.
""";

        try (ConsoleOutput actual = new ConsoleOutput()) {
            Main.main(values);

            Assert.assertEquals(expected, actual.getOutput());
        }
    }

    @Test
    public void testMain_emptyParameters_success() {
        final int expected = -13;

        int actual;
        try (ConsoleOutput result = new ConsoleOutput()) {
            actual = Main.main(null);

            Assert.assertEquals("", result.getOutput());
        }

        Assert.assertEquals(expected, actual);
    }
}

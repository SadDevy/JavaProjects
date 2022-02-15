package Tests;

import Trees.GeneralComparator;
import Trees.StudentTestInfo;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Comparator;
import java.util.Date;

@RunWith(DataProviderRunner.class)
public class StudentsComparatorTests {
    @Test
    public void testConstructorWithOrder() {
        GeneralComparator<StudentTestInfo> a = new GeneralComparator<StudentTestInfo>(true);
        Assert.assertNotNull(a);
    }

    @Test
    public void testConstructorWithComparator() {
        GeneralComparator<StudentTestInfo> a = new GeneralComparator<>((Comparator<StudentTestInfo>) Comparator.naturalOrder(), true);
        Assert.assertNotNull(a);
    }

    @Test
    @UseDataProvider("getTestCompareTestCases")
    public void testCompare(StudentTestInfo a, StudentTestInfo b, int expected) {
        GeneralComparator<StudentTestInfo> comparator = new GeneralComparator<>(true);
        Assert.assertEquals(expected, comparator.compare(a, b));
    }

    @DataProvider
    public static Object[][] getTestCompareTestCases() {
        StudentTestInfo a = new StudentTestInfo("Сергей", "Сергеев", "Test", new Date(), 1);
        StudentTestInfo b = new StudentTestInfo("Петр", "Петров", "Test", new Date(), 2);

        return new Object[][] {
                {null, a, -1},
                {a, b, -1},
                {a, null, 1},
                {null, null, 0}
        };
    }
}

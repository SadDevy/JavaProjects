package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExporterTests.class,
        ImporterTests.class,
        RemoverTests.class,
        SerializerTests.class
})
public class AllTests {
}

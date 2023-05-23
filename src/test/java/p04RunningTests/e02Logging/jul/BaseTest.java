package p04RunningTests.e02Logging.jul;

import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.LogManager;

public class BaseTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        String logsFolderPath = "logs";
        String loggingPropertiesFile = "/logging.properties";

        Path logsFolder = Paths.get(logsFolderPath);
        Files.createDirectories(logsFolder);

        LogManager.getLogManager()
                .readConfiguration(BaseTest.class.getResourceAsStream(loggingPropertiesFile));
    }
}

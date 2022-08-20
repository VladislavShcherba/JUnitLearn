package e17TempDirectoryExtension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

class TempDirectoryTest {

    @Test
    void test1(@TempDir Path tempDir) throws IOException {
        String hello = "Hello, World!";
        System.out.println(tempDir);
        Path testFile = tempDir.resolve("test.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(testFile.toFile()));
        writer.println(hello);
        writer.close();
        Scanner scanner = new Scanner(new FileReader(testFile.toFile()));
        Assertions.assertEquals(hello, scanner.nextLine());
    }
}

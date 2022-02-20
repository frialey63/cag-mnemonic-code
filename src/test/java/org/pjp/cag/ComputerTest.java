package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.dev.PaperTape;

public class ComputerTest {

    @Test
    public void testGetInputStream() throws IOException {
        assertTrue(Computer.getInputStream(Files.createTempFile("tmp-", ".tmp").toFile()) instanceof FileInputStream);
    }

    @Test
    public void testGetInputStreamNull() throws IOException {
        assertEquals(System.in, Computer.getInputStream(null));
    }

    @Test
    public void testInnerMain() throws IOException, URISyntaxException {
        PrintStream prevOut = PaperTape.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

            PaperTape.setOut(printStream);

            Path path = Paths.get(ClassLoader.getSystemResource("simple_test.txt").toURI());

            Computer.innerMain(path, false);

            String printText = outputStream.toString();

            assertEquals("SIMPLE TEST\n 3.141593  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testInnerMainUnusual() throws IOException, URISyntaxException {
        PrintStream prevOut = PaperTape.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

            PaperTape.setOut(printStream);

            Path path = Paths.get(ClassLoader.getSystemResource("unusual.txt").toURI());

            Computer.innerMain(path, false);

            String printText = outputStream.toString();

            assertEquals("SIMPLE TEST\n 0.000000  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

}

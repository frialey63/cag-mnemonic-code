package org.pjp.cag;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.Test;

public class ComputerTest {

    private static final String PROGRAM = "data/pi.txt";

    @Test
    public void testInnerMain() throws IOException {
        PrintStream prevOut = System.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

            System.setOut(printStream);

            Computer.innerMain(Paths.get(PROGRAM));

            String printText = outputStream.toString();

            assertEquals("SIMPLE TEST\n3.141593", printText);

        } finally {
            System.setOut(prevOut);
        }
    }

}

package org.pjp.cag;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.io.PaperTape;

public class ComputerTest {

    private static final String PI = "data/pi.txt";

    @Test
    public void testMain() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

            PaperTape.setOut(printStream);

            Computer.main(new String[] {PI });

            String printText = outputStream.toString();

            assertEquals("SIMPLE TEST\n3.141593", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

}

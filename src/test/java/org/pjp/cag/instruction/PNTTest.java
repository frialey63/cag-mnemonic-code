package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Ignore;
import org.junit.Test;
import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;

public class PNTTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) Math.PI);

            PNT instruction = new PNT(false, 1, 6);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals(" 3.141593  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteLeadingZeros() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) 3.14);

            PNT instruction = new PNT(false, 6, 1);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("      3.1  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteLeadingZeros2() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) 0.14);

            PNT instruction = new PNT(false, 6, 1);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("      0.1  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteNegative() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) -Math.PI);

            PNT instruction = new PNT(false, 1, 6);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("-3.141593  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteNegativeLeadingZeros() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) -3.14);

            PNT instruction = new PNT(false, 6, 1);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("     -3.1  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteNegativeLeadingZeros2() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) -0.14);

            PNT instruction = new PNT(false, 6, 1);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("     -0.1  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Ignore
    @Test
    public void testExecuteNumberExceedsFormat() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) (10 * Math.PI));

            PNT instruction = new PNT(false, 1, 6);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals(" 3.141593e+01  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

}

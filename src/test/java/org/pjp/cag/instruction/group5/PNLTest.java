package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.dev.PaperTape;

public class PNLTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) Math.PI);

            PNL instruction = new PNL(false);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("\n", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

}

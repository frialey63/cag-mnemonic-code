package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;

public class PNLTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

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

package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.Store;

public class PNLTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = System.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

            System.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) Math.PI);

            PNL instruction = new PNL(false);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("\n", printText);

        } finally {
            System.setOut(prevOut);
        }
    }

}

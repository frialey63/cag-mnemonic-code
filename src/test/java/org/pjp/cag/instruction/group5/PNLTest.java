package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.CAGMnemonicCode;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.dev.PaperTape;

public class PNLTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, CAGMnemonicCode.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.accumulator().set((float) Math.PI);

            PNL instruction = new PNL(false, ZERO, ZERO);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("\n", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

}

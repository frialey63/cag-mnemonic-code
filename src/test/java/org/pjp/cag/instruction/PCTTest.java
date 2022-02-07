package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.Word;

public class PCTTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = System.out;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, "UTF-8")) {

            System.setOut(printStream);

            Store store = new Store();
            store.setLocation(110, Word.create(org.pjp.cag.Character.A));
            store.setRegister(3, 10);

            PCT instruction = new PCT(false, 100, 3);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("A", printText);

        } finally {
            System.setOut(prevOut);
        }
    }

}

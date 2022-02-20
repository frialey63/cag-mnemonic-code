package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.Store.ZERO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.Instruction;

public class PCTTest {

    @Test
    public void testExecute() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setLocation(110, Word.create('A'));
            store.setRegister(3, 10);

            PCT instruction = new PCT(false, 100, 3);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("A", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteCharacterFollowingNumber() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) Math.PI);
            store.setLocation(110, Word.create('A'));
            store.setRegister(3, 10);

            Instruction instruction = new PNT(false, 1, 6);
            instruction.execute(store);

            instruction = new PCT(false, 100, 3);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals(" 3.141593  A", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteNumberFollowingCharacter() throws IOException {
        PrintStream prevOut = PaperTape.out;

        try (OutputStream outputStream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(outputStream, true, Computer.CHARSET)) {

            PaperTape.setOut(printStream);

            Store store = new Store();
            store.setAccumulator((float) Math.PI);
            store.setLocation(110, Word.create('A'));
            store.setRegister(3, 10);

            Instruction instruction = new PCT(false, 100, 3);
            instruction.execute(store);

            instruction = new PNT(false, 1, 6);
            instruction.execute(store);

            String printText = outputStream.toString();

            assertEquals("A 3.141593  ", printText);

        } finally {
            PaperTape.setOut(prevOut);
        }
    }

    @Test
    public void testExecuteInadmissableCharacter() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setLocation(100, Word.create(123));

            Instruction pct = new PCT(false, 100, ZERO);
            pct.execute(store);
        });

        assertEquals(RunningError.ERR_16.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}

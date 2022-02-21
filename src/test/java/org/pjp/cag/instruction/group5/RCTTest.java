package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.pjp.cag.CAGMnemonicCode1964;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.Instruction;

public class RCTTest {

    @Test
    public void testExecute() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("A".getBytes()), CAGMnemonicCode1964.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            RCT instruction = new RCT(false, 100, 3);
            instruction.execute(store);

            assertEquals('A', (char) store.getLocation(110).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteCharacterFollowingNumber1() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("123.456\nA".getBytes()), CAGMnemonicCode1964.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            Instruction instruction = new RNT(false, ZERO, ZERO);
            instruction.execute(store);

            instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            assertEquals(123.456f, store.accumulator().get(), 0.001f);
            assertEquals('A', (char) store.getLocation(120).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteCharacterFollowingNumber2() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("123.456  A".getBytes()), CAGMnemonicCode1964.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            Instruction instruction = new RNT(false, ZERO, ZERO);
            instruction.execute(store);

            instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            assertEquals(123.456f, store.accumulator().get(), 0.001f);
            assertEquals('A', (char) store.getLocation(120).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

}

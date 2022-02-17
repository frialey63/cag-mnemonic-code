package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.Instruction;

public class RCTTest {

    @Test
    public void testExecute() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("A".getBytes()), Computer.CHARSET)) {

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

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("123.456\nA".getBytes()), Computer.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            Instruction instruction = new RNT(false);
            instruction.execute(store);

            instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            assertEquals(123.456f, store.getAccumulator(), 0.001f);
            assertEquals('A', (char) store.getLocation(120).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteCharacterFollowingNumber2() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("123.456  A".getBytes()), Computer.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            Instruction instruction = new RNT(false);
            instruction.execute(store);

            instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            assertEquals(123.456f, store.getAccumulator(), 0.001f);
            assertEquals('A', (char) store.getLocation(120).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

}

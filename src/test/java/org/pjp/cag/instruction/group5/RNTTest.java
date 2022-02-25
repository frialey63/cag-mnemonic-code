package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.pjp.cag.CAGMnemonicCode;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.Instruction;
import org.pjp.cag.instruction.group2.STA;
import org.pjp.cag.test.TestConstants;

public class RNTTest {

    @Test
    public void testExecute() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("123.4567".getBytes()), CAGMnemonicCode.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();

            RNT instruction = new RNT(false, ZERO, ZERO);
            instruction.execute(store);

            assertEquals(123.4567f, store.accumulator().get(), 0.0001f);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteNumberFollowingCharacter() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("A123.456".getBytes()), CAGMnemonicCode.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            Instruction instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            instruction = new RNT(false, ZERO, ZERO);
            instruction.execute(store);

            assertEquals('A', (char) store.getLocation(120).character());
            assertEquals(123.456f, store.accumulator().get(), 0.001f);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteNumberFollowingNumber() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("987.654  123.456".getBytes()), CAGMnemonicCode.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();

            Instruction instruction = new RNT(false, ZERO, ZERO);
            instruction.execute(store);

            instruction = new STA(false, 100, ZERO);
            instruction.execute(store);

            instruction = new RNT(false, ZERO, ZERO);
            instruction.execute(store);

            assertEquals(987.654f, store.getLocation(100).number(), 0.001f);
            assertEquals(123.456f, store.accumulator().get(), 0.001f);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteNumberOutOfRange() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("131072".getBytes()), CAGMnemonicCode.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.accumulator().clear();

            RNT instruction = new RNT(false, 16, ZERO);
            instruction.execute(store);

            assertEquals(16, store.controlRegister().getAddress());
            assertEquals(0, store.accumulator().get(), TestConstants.DELTA);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteNumberFormatError() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("1A2".getBytes()), CAGMnemonicCode.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.accumulator().clear();
            store.setRegister(3, 10);

            RNT instruction = new RNT(false, 16, 3);
            instruction.execute(store);

            assertEquals(26, store.controlRegister().getAddress());
            assertEquals(0, store.accumulator().get(), TestConstants.DELTA);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

}

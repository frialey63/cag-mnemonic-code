package org.pjp.cag.instruction.group5;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.Instruction;

public class RNTTest {

    @Test
    public void testExecute() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("123.4567".getBytes()), Computer.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();

            RNT instruction = new RNT(false);
            instruction.execute(store);

            assertEquals(123.4567, store.getAccumulator(), TestConstants.PRECISION);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Test
    public void testExecuteNumberFollowingCharacter() throws IOException {
        InputStreamReader prevIn = PaperTape.in;

        try (InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("A123.456".getBytes()), Computer.CHARSET)) {

            PaperTape.setIn(inputStreamReader);

            Store store = new Store();
            store.setRegister(3, 10);

            Instruction instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            instruction = new RNT(false);
            instruction.execute(store);

            assertEquals('A', (char) store.getLocation(120).character());
            assertEquals(123.456, store.getAccumulator(), TestConstants.PRECISION);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

}

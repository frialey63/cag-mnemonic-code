package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;
import org.pjp.cag.io.PaperTape;

public class RCTTest {

    @Test
    public void testExecute() throws IOException {
        InputStream prevIn = PaperTape.in;

        try (InputStream inputStream = new ByteArrayInputStream("A".getBytes())) {

            PaperTape.setIn(inputStream);

            Store store = new Store();
            store.setRegister(3, 10);

            RCT instruction = new RCT(false, 100, 3);
            instruction.execute(store);

            assertEquals('A', (char) store.getLocation(110).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

    @Ignore // FIXME paper tape reader using Java input streams
    @Test
    public void testExecuteCharacterFollowingNumber() throws IOException {
        InputStream prevIn = PaperTape.in;

        try (InputStream inputStream = new ByteArrayInputStream("123.456\nA".getBytes())) {

            PaperTape.setIn(inputStream);

            Store store = new Store();

            Instruction instruction = new RNT(false);
            instruction.execute(store);

            instruction = new RCT(false, 110, 3);
            instruction.execute(store);

            assertEquals(123.456, store.getAccumulator(), TestConstants.PRECISION);
            assertEquals('A', (char) store.getLocation(120).character());

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

}

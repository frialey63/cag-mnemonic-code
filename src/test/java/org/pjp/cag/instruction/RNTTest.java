package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;
import org.pjp.cag.io.PaperTape;

public class RNTTest {

    @Test
    public void testExecute() throws IOException {
        InputStream prevIn = PaperTape.in;

        try (InputStream inputStream = new ByteArrayInputStream("123.4567".getBytes())) {

            PaperTape.setIn(inputStream);

            Store store = new Store();

            RNT instruction = new RNT(false);
            instruction.execute(store);

            assertEquals(123.4567, store.getAccumulator(), TestConstants.PRECISION);

        } finally {
            PaperTape.setIn(prevIn);
        }
    }

}

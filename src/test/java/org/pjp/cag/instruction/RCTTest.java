package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.pjp.cag.Store;
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

}

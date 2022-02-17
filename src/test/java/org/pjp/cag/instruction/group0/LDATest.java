package org.pjp.cag.instruction.group0;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.test.TestConstants;

public class LDATest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.clearAccumulator();
        store.setLocation(110, Word.create(456));
        store.setRegister(3, 10);

        LDA instruction = new LDA(false, 100, 3);
        instruction.execute(store);

        assertEquals(456.0f, store.getAccumulator(), TestConstants.DELTA);
    }

}

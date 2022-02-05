package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class SQTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(678);

        SQT instruction = new SQT(false, 16);
        instruction.execute(store);

        assertEquals(Math.sqrt(678), store.getAccumulator(), TestConstants.PRECISION);
    }

    @Test
    public void testExecuteError() {
        Store store = new Store();
        store.setAccumulator(-1);

        SQT instruction = new SQT(false, 16);
        instruction.execute(store);

        assertEquals(16, store.getControlAddress());
    }

}

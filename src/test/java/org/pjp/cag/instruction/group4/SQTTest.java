package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class SQTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(678);

        SQT instruction = new SQT(false, 16, ZERO);
        instruction.execute(store);

        assertEquals((float) Math.sqrt(678), store.getAccumulator(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteError() {
        Store store = new Store();
        store.setAccumulator(-1);

        SQT instruction = new SQT(false, 16, ZERO);
        instruction.execute(store);

        assertEquals(16, store.controlRegister().getAddress());
    }

    @Test
    public void testExecuteErrorModified() {
        Store store = new Store();
        store.setAccumulator(-1);
        store.setRegister(3, 10);

        SQT instruction = new SQT(false, 16, 3);
        instruction.execute(store);

        assertEquals(26, store.controlRegister().getAddress());
    }

}

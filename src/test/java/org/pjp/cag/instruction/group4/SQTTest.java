package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.test.TestConstants;

public class SQTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(678);

        SQT instruction = new SQT(false, 16, ZERO);
        instruction.execute(store);

        assertEquals((float) Math.sqrt(678), store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteError() {
        Store store = new Store();
        store.accumulator().set(-1);

        SQT instruction = new SQT(false, 16, ZERO);
        instruction.execute(store);

        assertEquals(16, store.controlRegister().getAddress());
        assertEquals(-1, store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteErrorModified() {
        Store store = new Store();
        store.accumulator().set(-1);
        store.setRegister(3, 10);

        SQT instruction = new SQT(false, 16, 3);
        instruction.execute(store);

        assertEquals(26, store.controlRegister().getAddress());
        assertEquals(-1, store.accumulator().get(), TestConstants.DELTA);
    }

}

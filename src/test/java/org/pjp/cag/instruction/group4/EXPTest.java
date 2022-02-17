package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class EXPTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(1);

        EXP instruction = new EXP(false, 16);
        instruction.execute(store);

        assertEquals((float) Math.exp(1), store.getAccumulator(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteError() {
        Store store = new Store();
        store.setAccumulator(40.5f);

        EXP instruction = new EXP(false, 16);
        instruction.execute(store);

        assertEquals(16, store.getControlAddress());
    }

    @Test
    public void testExecuteErrorModified() {
        Store store = new Store();
        store.setAccumulator(40.5f);
        store.setRegister(3, 10);

        EXP instruction = new EXP(false, 16, 3);
        instruction.execute(store);

        assertEquals(26, store.getControlAddress());
    }

}

package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.test.TestConstants;

public class ENTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(8.6f);

        ENT instruction = new ENT(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals(8, store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteNegative() {
        Store store = new Store();
        store.accumulator().set(-8.6f);

        ENT instruction = new ENT(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals(-9, store.accumulator().get(), TestConstants.DELTA);
    }

}

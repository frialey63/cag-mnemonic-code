package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class ENTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(1.7f);

        ENT instruction = new ENT(false);
        instruction.execute(store);

        assertEquals(1, store.getAccumulator(), TestConstants.PRECISION);
    }

    @Test
    public void testExecuteNegative() {
        Store store = new Store();
        store.setAccumulator(-1.2f);

        ENT instruction = new ENT(false);
        instruction.execute(store);

        assertEquals(-1, store.getAccumulator(), TestConstants.PRECISION);
    }

}

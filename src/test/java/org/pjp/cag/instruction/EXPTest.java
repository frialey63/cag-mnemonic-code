package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class EXPTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(1);

        EXP instruction = new EXP(false, 16);
        instruction.execute(store);

        assertEquals(Math.exp(1), store.getAccumulator(), TestConstants.PRECISION);
    }

}

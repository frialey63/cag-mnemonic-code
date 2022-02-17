package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class SINTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(0.5f);

        SIN instruction = new SIN(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals((float) Math.sin(0.5), store.getAccumulator(), TestConstants.DELTA);
    }

}

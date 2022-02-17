package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class SINTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(0.5f);

        SIN instruction = new SIN(false);
        instruction.execute(store);

        assertEquals(Math.sin(0.5), store.getAccumulator(), TestConstants.PRECISION);
    }

}

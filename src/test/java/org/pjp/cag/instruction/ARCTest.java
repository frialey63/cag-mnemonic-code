package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class ARCTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(1);

        ARC instruction = new ARC(false);
        instruction.execute(store);

        assertEquals(Math.atan(1), store.getAccumulator(), TestConstants.PRECISION);
    }

}

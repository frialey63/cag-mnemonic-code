package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.test.TestConstants;

public class ARCTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(1);

        ARC instruction = new ARC(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals((float) Math.atan(1), store.accumulator().get(), TestConstants.DELTA);
    }

}

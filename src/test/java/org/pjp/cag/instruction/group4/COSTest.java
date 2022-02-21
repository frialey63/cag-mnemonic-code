package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class COSTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(0.5f);

        COS instruction = new COS(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals((float) Math.cos(0.5), store.accumulator().get(), TestConstants.DELTA);
    }

}

package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class COSTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(0.5f);

        COS instruction = new COS(false);
        instruction.execute(store);

        assertEquals(Math.cos(0.5), store.getAccumulator(), TestConstants.PRECISION);
    }

}

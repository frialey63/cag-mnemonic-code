package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class COSTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(0.5f);

        COS instruction = new COS(false);
        instruction.execute(store);

        assertEquals((float) Math.cos(0.5), store.getAccumulator(), TestConstants.DELTA);
    }

}

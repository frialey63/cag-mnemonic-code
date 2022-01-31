package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class MLTNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(17);

        MLTN instruction = new MLTN(false, 13);
        instruction.execute(store);

        assertEquals(17 * 13, store.getAccumulator(), TestConstants.PRECISION);
    }

}

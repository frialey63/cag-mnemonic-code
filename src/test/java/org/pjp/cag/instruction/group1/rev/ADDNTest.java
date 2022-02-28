package org.pjp.cag.instruction.group1.rev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.test.TestConstants;

public class ADDNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(321);
        store.setRegister(3, 10);

        ADDN instruction = new ADDN(false, 999, 3);
        instruction.execute(store);

        assertEquals((float) (321 + 999 + 10), store.accumulator().get(), TestConstants.DELTA);
    }

}

package org.pjp.cag.instruction.group1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.test.TestConstants;

public class DIVNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(321);

        DIVN instruction = new DIVN(false, 999);
        instruction.execute(store);

        assertEquals((float) (321.0 / 999), store.getAccumulator(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteFloatingPointOverflow() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setAccumulator(321);

            DIVN instruction = new DIVN(false, 0);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_18.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}

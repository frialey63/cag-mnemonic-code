package org.pjp.cag.instruction.group0;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;
import org.pjp.cag.Word;

public class SUBTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(123);
        store.setLocation(110, Word.create(456));
        store.setRegister(3, 10);

        SUB instruction = new SUB(false, 100, 3);
        instruction.execute(store);

        assertEquals(123 - 456, store.getAccumulator(), TestConstants.PRECISION);
    }

}

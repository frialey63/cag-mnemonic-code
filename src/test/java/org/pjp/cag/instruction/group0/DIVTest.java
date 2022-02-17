package org.pjp.cag.instruction.group0;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;
import org.pjp.cag.Word;
import org.pjp.cag.instruction.Instruction;

public class DIVTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(123);
        store.setLocation(110, Word.create(456));
        store.setRegister(3, 10);

        Instruction instruction = new DIV(false, 100, 3);
        instruction.execute(store);

        assertEquals(123.0 / 456, store.getAccumulator(), TestConstants.PRECISION);
    }

}

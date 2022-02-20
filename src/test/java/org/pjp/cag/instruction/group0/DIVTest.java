package org.pjp.cag.instruction.group0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.Instruction;
import org.pjp.cag.test.TestConstants;

public class DIVTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(123);
        store.setLocation(110, Word.create(456));
        store.setRegister(3, 10);

        Instruction instruction = new DIV(false, 100, 3);
        instruction.execute(store);

        assertEquals((float) (123.0 / 456), store.getAccumulator(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteFloatingPointOverflow() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setAccumulator(123);
            store.setLocation(110, Word.create(0.0f));
            store.setRegister(3, 10);

            Instruction instruction = new DIV(false, 100, 3);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_18.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}

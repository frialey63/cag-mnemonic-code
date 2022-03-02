package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.cpu.Word;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.MachineInstruction;
import org.pjp.cag.instruction.group0.DIV;
import org.pjp.cag.instruction.group2.STA;

public class InterpreterTest {


    @Test
    public void testGetOrderWasNumber() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            store.setLocation(100, Word.create(666));

            Interpreter.getOrder(store, 100);
        });

        assertEquals(RunningError.ERR_11, exception.getError());
    }

    @Test
    public void testGetOrderWasCharacter() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            store.setLocation(100, Word.create('A'));

            Interpreter.getOrder(store, 100);
        });

        assertEquals(RunningError.ERR_11, exception.getError());
    }
    @Test
    public void testGetOrderAddressOutOfRange() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            Interpreter.getOrder(store, 1000);
        });

        assertEquals(RunningError.ERR_13, exception.getError());
    }

    @Test
    public void testExecuteMachineInstructionAddressOutOfRange() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            MachineInstruction sta = new STA(false, 1001, ZERO);
            Interpreter.executeMachineInstruction(store, sta);
        });

        assertEquals(RunningError.ERR_13, exception.getError());
    }

    @Test
    public void testExecuteMachineInstructionFloatingPointOverflow() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.accumulator().set(1);
            store.setLocation(100, Word.create(0));

            MachineInstruction div = new DIV(false, 100, ZERO);
            Interpreter.executeMachineInstruction(store, div);
        });

        assertEquals(RunningError.ERR_18, exception.getError());
    }

}

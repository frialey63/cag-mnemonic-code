package org.pjp.cag.instruction.group3.rev;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.Instruction;

/**
 * Loop to the address which may be modified based loop counter, the contents of register 5.
 * @author developer
 *
 */
public final class LOP extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public LOP(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        int loopCounter = store.decrementLoopCounter();

        if (loopCounter > 0) {
            int effectiveAddress = getEffectiveAddress(store);

            if (effectiveAddress >= Store.SIZE) {
                throw new RunningException(RunningError.ERR_10);
            }

            store.controlRegister().setAddress(effectiveAddress);
            result = false;
        }

        return result;
    }

}

package org.pjp.cag.instruction.group3;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Jump to subroutine at the address which may be modified.
 * Note return from subroutine is by JUN 0, 4
 * @author developer
 *
 */
public final class JSR extends MachineInstruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public JSR(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        int effectiveAddress = getEffectiveAddress(store);

        if (effectiveAddress >= Store.SIZE) {
            throw new RunningException(RunningError.ERR_10);
        }

        store.updateLinkAddress();
        store.controlRegister().setAddress(effectiveAddress);

        return false;
    }

}

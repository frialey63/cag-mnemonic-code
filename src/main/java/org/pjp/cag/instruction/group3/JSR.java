package org.pjp.cag.instruction.group3;

import org.pjp.cag.Store;
import org.pjp.cag.error.RunningError;
import org.pjp.cag.error.RunningException;
import org.pjp.cag.instruction.Instruction;

/**
 * Jump to subroutine at the address which may be modified.
 * Note return from subroutine is by JUN 0, 4
 * @author developer
 *
 */
public final class JSR extends Instruction {

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
        store.setControlAddress(effectiveAddress);

        return false;
    }

}

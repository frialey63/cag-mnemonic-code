package org.pjp.cag.instruction;

import org.pjp.cag.Store;

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

    /**
     * @param query The query flag
     * @param address The address
     */
    public JSR(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        store.updateLinkAddress();
        store.setControlAddress(getEffectiveAddress(store));
        return false;
    }

}

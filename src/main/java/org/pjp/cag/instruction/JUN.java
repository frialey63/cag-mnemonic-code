package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Jump unconditionally to the address which may be modified.
 * @author developer
 *
 */
public final class JUN extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public JUN(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    /**
     * @param query The query flag
     * @param address The address
     */
    public JUN(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        store.setControlAddress(getEffectiveAddress(store));
        return false;
    }

}

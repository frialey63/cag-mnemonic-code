package org.pjp.cag.instruction.group3;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

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

    @Override
    public boolean execute(Store store) {
        store.setControlAddress(getEffectiveAddress(store));
        return false;
    }

}

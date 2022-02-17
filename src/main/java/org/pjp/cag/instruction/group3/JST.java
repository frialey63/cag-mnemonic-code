package org.pjp.cag.instruction.group3;

import static org.pjp.cag.Store.ZERO;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Wait. TODO Jump when start button pressed
 * @author developer
 *
 */
public final class JST extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public JST(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        store.setControlAddress(ZERO);
        return false;
    }

}

package org.pjp.cag.instruction;

import static org.pjp.cag.Store.ZERO;

import org.pjp.cag.Store;

/**
 * Wait. TODO Jump when start button pressed
 * @author developer
 *
 */
public final class JST extends Instruction {

    /**
     * @param query The query flag
     */
    public JST(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        store.setControlAddress(ZERO);
        return false;
    }

}

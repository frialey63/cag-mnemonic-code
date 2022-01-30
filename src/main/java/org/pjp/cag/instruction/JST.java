package org.pjp.cag.instruction;

import static org.pjp.cag.Store.ZERO;

import org.pjp.cag.Store;

public final class JST extends Instruction {

    public JST(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        store.setControlAddress(ZERO);
        return false;
    }

}

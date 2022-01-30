package org.pjp.cag.instruction;

import org.pjp.cag.Store;

public final class LDAN extends Instruction {

    public LDAN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        store.setAccumulator(addressNumber);

        return true;
    }

}

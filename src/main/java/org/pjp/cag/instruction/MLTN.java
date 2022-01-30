package org.pjp.cag.instruction;

import org.pjp.cag.Store;

public final class MLTN extends Instruction {

    public MLTN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setAccumulator(addressNumber * accumulator);

        return true;
    }

}

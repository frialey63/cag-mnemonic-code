package org.pjp.cag.instruction;

import org.pjp.cag.Store;

public final class ARC extends Instruction {

    public ARC(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setAccumulator((float) Math.atan(accumulator));

        return true;
    }

}

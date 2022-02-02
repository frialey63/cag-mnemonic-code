package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the arctan of the value in the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class ARC extends Instruction {

    /**
     * @param query The query flag
     */
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

package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the arctan of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class ARC extends Instruction {

    /**
     * @param query The query flag
     * @param address TODO The address of the error handler
     */
    public ARC(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setAccumulator((float) Math.atan(accumulator));

        return true;
    }

}

package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the square root of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class SQT extends Instruction {

    /**
     * @param query The query flag
     * @param address The address of the error handler
     */
    public SQT(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        if (accumulator < 0) {
            store.setControlAddress(addressNumber);
            result = false;
        } else {
            store.setAccumulator((float) Math.sqrt(accumulator));
        }

        return result;
    }

}

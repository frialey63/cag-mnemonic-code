package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the natural log of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class LGN extends Instruction {

    /**
     * @param query The query flag
     * @param address The address of the error handler
     */
    public LGN(boolean query, int address) {
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
            store.setAccumulator((float) Math.log(accumulator));
        }

        return result;
    }

}
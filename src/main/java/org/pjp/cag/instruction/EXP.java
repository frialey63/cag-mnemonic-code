package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the exponent of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class EXP extends Instruction {

    /**
     * @param query The query flag
     * @param address The address of the error handler
     */
    public EXP(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.exp(accumulator));
        } catch (Exception e) {
            store.setControlAddress(addressNumber);
            result = false;
        }

        return result;
    }

}

package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the cosine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class COS extends Instruction {

    /**
     * @param query The query flag
     * @param address The address of the error handler
     */
    public COS(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.cos(accumulator));
        } catch (Exception e) {
            store.setControlAddress(addressNumber);
            result = false;
        }

        return result;
    }

}

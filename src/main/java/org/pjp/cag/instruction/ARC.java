package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the arc tangent of the accumulator and restore into the accumulator.
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
        boolean result = true;

        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.atan(accumulator));
        } catch (Exception e) {
            store.setControlAddress(addressNumber);
            result = false;
        }

        return result;
    }

}

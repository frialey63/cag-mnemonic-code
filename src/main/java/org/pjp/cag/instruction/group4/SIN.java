package org.pjp.cag.instruction.group4;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Calculate the sine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class SIN extends Instruction {

    /**
     * @param query The query flag
     */
    public SIN(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.sin(accumulator));
        } catch (Exception e) {
            store.setControlAddress(getAddress());
            result = false;
        }

        return result;
    }

}

package org.pjp.cag.instruction.group4;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

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
            store.setControlAddress(getAddress());
            result = false;
        }

        return result;
    }

}

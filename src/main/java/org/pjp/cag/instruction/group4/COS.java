package org.pjp.cag.instruction.group4;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Calculate the cosine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class COS extends Instruction {

    /**
     * @param query The query flag
     */
    public COS(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.cos(accumulator));
        } catch (Exception e) {
            store.setControlAddress(getAddress());
            result = false;
        }

        return result;
    }

}

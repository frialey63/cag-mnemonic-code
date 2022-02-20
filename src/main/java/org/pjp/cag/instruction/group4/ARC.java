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
     * @param address The address
     * @param modifier The modifier
     */
    public ARC(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setAccumulator((float) Math.atan(accumulator));

        return true;
    }

}

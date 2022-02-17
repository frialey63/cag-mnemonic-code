package org.pjp.cag.instruction.group4;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Calculate the natural log of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class LGN extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public LGN(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        if (accumulator <= 0) {
            store.setControlAddress(getEffectiveAddress(store));
            result = false;
        } else {
            store.setAccumulator((float) Math.log(accumulator));
        }

        return result;
    }

}

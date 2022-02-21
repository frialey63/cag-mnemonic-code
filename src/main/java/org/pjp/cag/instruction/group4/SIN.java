package org.pjp.cag.instruction.group4;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Calculate the sine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class SIN extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public SIN(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.accumulator().get();

        store.accumulator().set((float) Math.sin(accumulator));

        return true;
    }

}

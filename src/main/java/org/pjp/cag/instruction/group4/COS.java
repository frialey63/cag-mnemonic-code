package org.pjp.cag.instruction.group4;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Calculate the cosine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class COS extends MachineInstruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public COS(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.accumulator().get();

        store.accumulator().set((float) Math.cos(accumulator));

        return true;
    }

}

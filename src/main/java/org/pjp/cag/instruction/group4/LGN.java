package org.pjp.cag.instruction.group4;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Calculate the natural log of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class LGN extends MachineInstruction {

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

        float accumulator = store.accumulator().get();

        if (accumulator <= 0) {
            store.controlRegister().setAddress(getEffectiveAddress(store));
            result = false;
        } else {
            store.accumulator().set((float) Math.log(accumulator));
        }

        return result;
    }

}

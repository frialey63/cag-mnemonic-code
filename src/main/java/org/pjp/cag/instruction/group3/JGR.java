package org.pjp.cag.instruction.group3;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * If the accumulator is greater than zero then jump to the address which may be modified.
 * @author developer
 *
 */
public final class JGR extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public JGR(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    /**
     * @param query The query flag
     * @param address The address
     */
    public JGR(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        if (accumulator > 0) {
            store.setControlAddress(getEffectiveAddress(store));
            result = false;
        }

        return result;
    }

}

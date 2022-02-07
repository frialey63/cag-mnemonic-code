package org.pjp.cag.instruction;

import org.pjp.cag.Store;

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
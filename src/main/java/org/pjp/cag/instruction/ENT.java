package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Calculate the entier of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class ENT extends Instruction {

    /**
     * @param query The query flag
     */
    public ENT(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((int) accumulator);
        } catch (Exception e) {
            store.setControlAddress(addressNumber);
            result = false;
        }

        return result;
    }

}

package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Load the accumulator with the constant number.
 * @author developer
 *
 */
public final class SUBN extends Instruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public SUBN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setAccumulator(accumulator - addressNumber);

        return true;
    }

}

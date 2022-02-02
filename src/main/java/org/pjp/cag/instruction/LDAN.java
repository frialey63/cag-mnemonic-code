package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Load the accumulator with the constant number.
 * @author developer
 *
 */
public final class LDAN extends Instruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public LDAN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        store.setAccumulator(addressNumber);

        return true;
    }

}

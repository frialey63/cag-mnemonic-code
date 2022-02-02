package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Multiply the value in the accumulator by the constant number.
 * @author developer
 *
 */
public final class MLTN extends Instruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public MLTN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setAccumulator(addressNumber * accumulator);

        return true;
    }

}

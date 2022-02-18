package org.pjp.cag.instruction.group1;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Multiply the accumulator by the constant number.
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

        store.setAccumulator(accumulator * getLiteral());

        return true;
    }

}

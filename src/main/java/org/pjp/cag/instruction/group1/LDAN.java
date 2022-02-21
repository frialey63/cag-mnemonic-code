package org.pjp.cag.instruction.group1;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;

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
        store.accumulator().set(getLiteral());

        return true;
    }

}

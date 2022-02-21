package org.pjp.cag.instruction.group1;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Sum the accumulator with the constant number.
 * @author developer
 *
 */
public final class ADDN extends Instruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public ADDN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        int literal = getLiteral();

        store.accumulator().add(literal);

        return true;
    }

}

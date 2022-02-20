package org.pjp.cag.instruction.group1;

import org.pjp.cag.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.Instruction;

/**
 * Divide the value in the accumulator by the constant number.
 * @author developer
 *
 */
public final class DIVN extends Instruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public DIVN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        int literal = getLiteral();

        if (literal == 0) {
            throw new RunningException(RunningError.ERR_18);
        }

        store.setAccumulator(accumulator / literal);

        return true;
    }

}

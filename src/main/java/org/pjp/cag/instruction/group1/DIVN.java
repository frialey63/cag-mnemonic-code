package org.pjp.cag.instruction.group1;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Divide the value in the accumulator by the constant number.
 * @author developer
 *
 */
public final class DIVN extends MachineInstruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public DIVN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        int literal = getLiteral();

        store.accumulator().div(literal);

        return true;
    }

}

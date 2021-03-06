package org.pjp.cag.instruction.group1;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Load the accumulator with the constant number.
 * @author developer
 *
 */
public final class LDAN extends MachineInstruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public LDAN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        int literal = getLiteral();

        store.accumulator().set(literal);

        return true;
    }

}

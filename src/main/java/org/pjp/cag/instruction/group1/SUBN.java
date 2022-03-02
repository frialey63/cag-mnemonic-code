package org.pjp.cag.instruction.group1;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Subtract from the accumulator the constant number.
 * @author developer
 *
 */
public final class SUBN extends MachineInstruction {

    /**
     * @param query The query flag
     * @param number The number
     */
    public SUBN(boolean query, int number) {
        super(query, number);
    }

    @Override
    public boolean execute(Store store) {
        int literal = getLiteral();

        store.accumulator().sub(literal);

        return true;
    }

}

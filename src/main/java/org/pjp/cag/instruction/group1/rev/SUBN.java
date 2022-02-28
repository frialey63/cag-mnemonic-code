package org.pjp.cag.instruction.group1.rev;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Subtract from the accumulator the constant number.
 * @author developer
 *
 */
public final class SUBN extends Instruction {

    /**
     * @param query The query flag
     * @param number The number
     * @param modifier The modifier
     */
    public SUBN(boolean query, int number, int modifier) {
        super(query, number, modifier);
    }

    @Override
    public boolean execute(Store store) {
        int literal = getLiteral();
        int mod = getModification(store);

        store.accumulator().sub(literal);
        store.accumulator().add(mod);

        return true;
    }

}

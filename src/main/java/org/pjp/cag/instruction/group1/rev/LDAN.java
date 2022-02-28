package org.pjp.cag.instruction.group1.rev;

import org.pjp.cag.cpu.Store;
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
     * @param modifier The modifier
     */
    public LDAN(boolean query, int number, int modifier) {
        super(query, number, modifier);
    }

    @Override
    public boolean execute(Store store) {
        int literal = getLiteral();
        int mod = getModification(store);

        store.accumulator().set(literal);
        store.accumulator().add(mod);

        return true;
    }

}

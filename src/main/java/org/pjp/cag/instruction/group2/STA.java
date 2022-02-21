package org.pjp.cag.instruction.group2;

import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.instruction.Instruction;

/**
 * Store the accumulator to the specified location of store as a number.
 * @author developer
 *
 */
public final class STA extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public STA(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.accumulator().get();

        store.setLocation(getEffectiveAddress(store), Word.create(accumulator));

        return true;
    }

}

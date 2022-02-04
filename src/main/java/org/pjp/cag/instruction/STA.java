package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.Word;

/**
 * Load the accumulator with the number read from the specified location of store.
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

    /**
     * @param query The query flag
     * @param address The address
     */
    public STA(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        store.setLocation(getEffectiveAddress(store), Word.create(accumulator));

        return true;
    }

}

package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.Word;

/**
 * Load the accumulator with the number read from the specified location of store.
 * @author developer
 *
 */
public final class LDA extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public LDA(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        Word word = store.getLocation(getEffectiveAddress(store));

        store.setAccumulator(word.number());

        return true;
    }

}

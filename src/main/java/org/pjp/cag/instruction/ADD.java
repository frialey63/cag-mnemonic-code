package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.Word;

/**
 * Sum the accumulator with the number read from the specified location of store.
 * @author developer
 *
 */
public final class ADD extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public ADD(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    /**
     * @param query The query flag
     * @param address The address
     */
    public ADD(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        Word word = store.getLocation(getEffectiveAddress(store));

        float accumulator = store.getAccumulator();

        store.setAccumulator(accumulator + word.number());

        return true;
    }

}

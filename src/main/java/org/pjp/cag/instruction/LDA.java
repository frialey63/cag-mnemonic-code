package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.Word;

public final class LDA extends Instruction {

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

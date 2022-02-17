package org.pjp.cag.instruction.group5;

import org.pjp.cag.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.Instruction;

/**
 * Print the character at the address which may be modified.
 * @author developer
 *
 */
public final class PCT extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public PCT(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    /**
     * @param query The query flag
     * @param address The address
     */
    public PCT(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        char character = store.getLocation(getEffectiveAddress(store)).character();

        PaperTape.out.printf("%c", character);

        return true;
    }

}

package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;

/**
 * TODO charset for printing with OutputStreamWriter
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

    @Override
    public boolean execute(Store store) {
        Character character = store.getLocation(getEffectiveAddress(store)).character();

        String format = "%s";

        PaperTape.out.printf(format, character);

        return true;
    }

}

package org.pjp.cag.instruction;

import org.pjp.cag.Character;
import org.pjp.cag.Store;

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

    @Override
    public boolean execute(Store store) {
        Character character = store.getLocation(getEffectiveAddress(store)).character();

        String format = "%s";

        System.out.printf(format, character);

        return true;
    }

}

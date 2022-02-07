package org.pjp.cag.instruction;

import org.pjp.cag.Computer;
import org.pjp.cag.Store;

/**
 * Print the new line.
 * @author developer
 *
 */
public final class PNL extends Instruction {

    /**
     * @param query The query flag
     */
    public PNL(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        Computer.tape.println();
        return true;
    }

}

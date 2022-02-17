package org.pjp.cag.instruction.group5;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;
import org.pjp.cag.io.PaperTape;

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
        PaperTape.out.println();
        return true;
    }

}

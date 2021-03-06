package org.pjp.cag.instruction.group5;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Print the new line.
 * @author developer
 *
 */
public final class PNL extends MachineInstruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public PNL(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        PaperTape.out.println();
        return true;
    }

}

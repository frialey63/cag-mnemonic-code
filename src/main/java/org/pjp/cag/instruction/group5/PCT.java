package org.pjp.cag.instruction.group5;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.exception.internal.FaultyWordException;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Print the character at the address which may be modified.
 * @author developer
 *
 */
public final class PCT extends MachineInstruction {

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
        try {
            char character = store.getLocation(getEffectiveAddress(store)).character();

            PaperTape.out.printf("%c", character);
        } catch (FaultyWordException e) {
            throw new RunningException(RunningError.ERR_16);
        }

        return true;
    }

}

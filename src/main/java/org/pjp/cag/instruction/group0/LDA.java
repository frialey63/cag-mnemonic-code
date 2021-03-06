package org.pjp.cag.instruction.group0;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.cpu.Word;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.exception.internal.FaultyWordException;
import org.pjp.cag.instruction.MachineInstruction;

/**
 * Load the accumulator with the number read from the specified location of store.
 * @author developer
 *
 */
public final class LDA extends MachineInstruction {

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

        try {
            store.accumulator().set(word.number());
        } catch (FaultyWordException e) {
            throw new RunningException(RunningError.ERR_15);
        }

        return true;
    }

}

package org.pjp.cag.instruction.group3;

import org.pjp.cag.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.Instruction;

/**
 * If the accumulator is greater than zero then jump to the address which may be modified.
 * @author developer
 *
 */
public final class JGR extends Instruction {

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public JGR(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        boolean result = true;

        float accumulator = store.getAccumulator();

        if (accumulator > 0) {
            int effectiveAddress = getEffectiveAddress(store);

            if (effectiveAddress >= Store.SIZE) {
                throw new RunningException(RunningError.ERR_10);
            }

            store.setControlAddress(effectiveAddress);
            result = false;
        }

        return result;
    }

}

package org.pjp.cag.instruction.group4;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calculate the sine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class SIN extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIN.class);

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public SIN(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.sin(accumulator));
        } catch (Exception e) {
            LOGGER.error("caught unexpected Exception while attempting SIN calculation", e);
        }

        return true;
    }

}

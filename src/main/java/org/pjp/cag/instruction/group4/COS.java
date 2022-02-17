package org.pjp.cag.instruction.group4;

import org.pjp.cag.Store;
import org.pjp.cag.instruction.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calculate the cosine of the accumulator and restore into the accumulator.
 * @author developer
 *
 */
public final class COS extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(COS.class);

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public COS(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        try {
            store.setAccumulator((float) Math.cos(accumulator));
        } catch (Exception e) {
            LOGGER.error("caught unexpected Exception while attempting COS calculation", e);
        }

        return true;
    }

}

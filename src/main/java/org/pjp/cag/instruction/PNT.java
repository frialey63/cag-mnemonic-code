package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;

/**
 * Print the accumulator using the specified numeric format.
 * @author developer
 *
 */
public final class PNT extends Instruction {

    /**
     * @param query The query flag
     * @param integralDigits The number of integer digits
     * @param fractionalDigits The number of fractional digits
     */
    public PNT(boolean query, int integralDigits, int fractionalDigits) {
        super(query, integralDigits, fractionalDigits);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        int width = addressNumber + modifier;
        int precision = modifier;

        String format = "%" + width + "." + precision + "f";

        PaperTape.out.printf(format, accumulator);

        return true;
    }

}

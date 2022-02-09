package org.pjp.cag.instruction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Print the accumulator using the specified numeric format.
 * @author developer
 *
 */
public final class PNT extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(PNT.class);

    /**
     * @param query The query flag
     * @param integralDigits The number of integer digits
     * @param fractionalDigits The number of fractional digits
     */
    public PNT(boolean query, int integralDigits, int fractionalDigits) {
        super(query, integralDigits, fractionalDigits);
    }

    /**
     * @param query The query flag
     * @param integralDigits The number of integer digits
     */
    public PNT(boolean query, int integralDigits) {
        super(query, integralDigits);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        int width = addressNumber + modifier;
        int precision = modifier;

        String format = "%" + width + "." + precision + "f";

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(PaperTape.out, Computer.CHARSET))) {
            writer.write(String.format(format, accumulator));

        } catch (IOException e) {
           LOGGER.error("caught IOException while attempting to write number to tape", e);
        }

        return true;
    }

}

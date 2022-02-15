package org.pjp.cag.instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.exception.NumberReadException;
import org.pjp.cag.io.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read the number to the address which may be modified.
 * @author developer
 *
 */
public final class RNT extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(RNT.class);

    private static final int MAX_DECIMAL_DIGITS = 7;

    /**
     * @param query The query flag
     */
    public RNT(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(PaperTape.in, Computer.CHARSET))) {
            float number = Float.parseFloat(checkDecimalDigits(reader.readLine()));

            store.setAccumulator(number);

        } catch (IOException e) {
            LOGGER.error("caught IOException while attempting to read number from tape", e);
        }

        return true;
    }

    private String checkDecimalDigits(String line) throws IOException {
        String temp = line.trim().replaceAll("\\.", "").replaceAll("\\+", "").replaceAll("\\-", "").toLowerCase();

        String[] digits = temp.split("e");

        if (digits[0].length() > MAX_DECIMAL_DIGITS) {
            throw new NumberReadException("number read has more than 7 decimal digits");
        }

        return line;
    }

}

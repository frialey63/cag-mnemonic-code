package org.pjp.cag.instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pjp.cag.Computer;
import org.pjp.cag.Store;
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

    /**
     * @param query The query flag
     */
    public RNT(boolean query) {
        super(query);
    }

    @Override
    public boolean execute(Store store) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(PaperTape.in, Computer.CHARSET))) {
            float number = Float.parseFloat(reader.readLine());

            store.setAccumulator(number);

        } catch (IOException e) {
            LOGGER.error("caught IOException while attempting to read number from tape", e);
        }

        return true;
    }

}

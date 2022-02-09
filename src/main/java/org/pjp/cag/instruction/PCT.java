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
 * Print the character at the address which may be modified.
 * @author developer
 *
 */
public final class PCT extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(PCT.class);

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public PCT(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    /**
     * @param query The query flag
     * @param address The address
     */
    public PCT(boolean query, int address) {
        super(query, address);
    }

    @Override
    public boolean execute(Store store) {
        char character = store.getLocation(getEffectiveAddress(store)).character();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(PaperTape.out, Computer.CHARSET))) {
            writer.write(character);

        } catch (IOException e) {
           LOGGER.error("caught IOException while attempting to write character to tape", e);
        }

        return true;
    }

}

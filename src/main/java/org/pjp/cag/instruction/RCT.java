package org.pjp.cag.instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pjp.cag.Computer;
import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.io.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read the character to the address which may be modified.
 * @author developer
 *
 */
public final class RCT extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(RCT.class);

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public RCT(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(PaperTape.in, Computer.CHARSET))) {
            char character = (char) reader.read();

            store.setLocation(getEffectiveAddress(store), Word.create(character));

        } catch (IOException e) {
            LOGGER.error("caught IOException while attempting to read character from tape", e);
        }

        return true;
    }

}

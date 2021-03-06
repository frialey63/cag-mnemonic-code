package org.pjp.cag.instruction.group5;

import java.io.IOException;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.cpu.Word;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.MachineInstruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read the character to the address which may be modified.
 * @author developer
 *
 */
public final class RCT extends MachineInstruction {

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
        try {
            char character = (char) PaperTape.in.read();

            store.setLocation(getEffectiveAddress(store), Word.create(character));
        } catch (IOException e) {
            LOGGER.error("caught IOException while attempting to read character from tape", e);
        }

        return true;
    }

}

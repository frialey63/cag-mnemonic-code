package org.pjp.cag.instruction.group5;

import java.io.IOException;
import java.io.InputStreamReader;

import org.pjp.cag.Store;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.instruction.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read the number to the address which may be modified.
 * @author developer
 *
 */
public final class RNT extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(RNT.class);

    private static String readline(InputStreamReader reader) throws IOException {
        StringBuffer sb = new StringBuffer();

        boolean prevSpace = false;

        int ch;
        while ((ch = reader.read()) != -1) {
            if (ch == '\n') {
                break;
            }

            boolean space = (ch == ' ');

            if (space && prevSpace) {
                break;
            }

            prevSpace = space;

            sb.append((char) ch);
        }

        return sb.toString();
    }

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public RNT(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        try {
            float number = Float.parseFloat(readline(PaperTape.in));

            store.setAccumulator(number);
        } catch (IOException e) {
            LOGGER.error("caught IOException while attempting to read number from tape", e);
        }

        return true;
    }

}

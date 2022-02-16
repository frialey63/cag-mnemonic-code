package org.pjp.cag.instruction;

import java.io.IOException;
import java.io.InputStreamReader;

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

    private static String readline(InputStreamReader reader) throws IOException {
        StringBuffer sb = new StringBuffer();

        int ch;
        while ((ch = reader.read()) != -1) {
            if (ch == '\n') {
                break;
            }

            sb.append((char) ch);
        }

        return sb.toString();
    }

    /**
     * @param query The query flag
     */
    public RNT(boolean query) {
        super(query);
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

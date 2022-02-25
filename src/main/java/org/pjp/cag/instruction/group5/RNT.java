package org.pjp.cag.instruction.group5;

import java.io.IOException;
import java.io.InputStreamReader;

import org.pjp.cag.CAGMnemonicCode;
import org.pjp.cag.cpu.Store;
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
        boolean result = true;

        try {
            String line = readline(PaperTape.in);

            try {
                long integer = Long.parseLong(line);

                if (Math.abs(integer) > CAGMnemonicCode.MAX_INT) {
                    store.controlRegister().setAddress(getEffectiveAddress(store));
                    result = false;
                } else {
                    store.accumulator().set(integer);

                }
            } catch (NumberFormatException e) {
                try {
                    float number = Float.parseFloat(line);

                    store.accumulator().set(number);
                } catch (NumberFormatException e1) {
                    // seems reasonable to use error handler as hinted in Resurrection #71 but not in A.T.Gough paper, also for MAX_INT checking
                    store.controlRegister().setAddress(getEffectiveAddress(store));
                    result = false;
                }
            }

        } catch (IOException e) {
            LOGGER.error("caught IOException while attempting to read number from tape", e);
        }

        return result;
    }

}

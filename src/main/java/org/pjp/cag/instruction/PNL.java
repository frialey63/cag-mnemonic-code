package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * Print the new line.
 * @author developer
 *
 */
public final class PNL extends Instruction {

    /**
     * @param query The query flag
     * @param integralDigits The number of integer digits
     * @param fractionalDigits The number of fractional digits
     */
    public PNL(boolean query, int integralDigits, int fractionalDigits) {
        super(query, integralDigits, fractionalDigits);
    }

    @Override
    public boolean execute(Store store) {
        System.out.println();
        return true;
    }

}

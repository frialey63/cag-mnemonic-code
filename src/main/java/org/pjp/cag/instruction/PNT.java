package org.pjp.cag.instruction;

import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;

/**
 * Print the accumulator using the specified numeric format.
 * @author developer
 *
 */
public final class PNT extends Instruction {

    private static final int FOUR = 4;

    private static final int TEN = 10;

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

        int width = addressNumber + modifier + (accumulator < 0 ? 2 : 1);
        int precision = modifier;

        String leadingSpace = (accumulator > 0) ? " " : "";
        String trailingSpace = "  ";
        String format;

        if (Math.abs(accumulator) > Math.pow(TEN, addressNumber)) {
            format = leadingSpace + "%0" + width + "." + precision + "e" + trailingSpace;   // TODO more scientific number formatting
        } else {
            format = leadingSpace + "%0" + width + "." + precision + "f" + trailingSpace;
        }

        String str = String.format(format, accumulator);

        // convert leading zeros to leading spaces
        if (accumulator < 0) {
            while (str.matches(" *\\-0[0-9]{1}.*")) {
                str = str.replaceFirst("-0", " -");
            }
        } else {
            while (str.matches(" *0[0-9]{1}.*")) {
                str = str.replaceFirst("0", " ");
            }
        }

        assert str.length() == (addressNumber + modifier + FOUR);

        PaperTape.out.print(str);

        return true;
    }

}

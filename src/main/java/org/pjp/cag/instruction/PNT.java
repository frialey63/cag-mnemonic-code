package org.pjp.cag.instruction;

import java.text.DecimalFormatSymbols;

import org.pjp.cag.Store;
import org.pjp.cag.io.PaperTape;

/**
 * Print the accumulator using the specified numeric format.
 * @author developer
 *
 */
public final class PNT extends Instruction {

    private static final int FIFTEEN = 15;

    private static final int SEVEN = 7;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int BASE = 10;

    private static final String ZERO = String.valueOf(new DecimalFormatSymbols().getZeroDigit());

    private static String convertLeadingZeros(float number, String str) {
        if (number < 0) {
            while (str.matches(" *\\-0[0-9]{1}.*")) {
                str = str.replaceFirst("-" + ZERO, " -");
            }
        } else {
            while (str.matches(" *0[0-9]{1}.*")) {
                str = str.replaceFirst(ZERO, " ");
            }
        }

        return str;
    }

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

        String str;

        if (Math.abs(accumulator) > Math.pow(BASE, addressNumber) || (addressNumber + modifier > SEVEN) || (addressNumber == 0 && modifier == 0)) {
            str = formatFloat(accumulator);
        } else {
            str = formatFixed(accumulator);
        }

        PaperTape.out.print(str);

        return true;
    }

    private String formatFixed(float number) {
        int width = addressNumber + modifier;
        int precision = modifier;

        if (precision == 0) {
            // integer, negative needs sign but positive has leadingSpace
            width += (number < 0 ? 1 : 0);
        } else {
            // decimal, also needs decimal point
            width += (number < 0 ? 2 : 1);
        }

        String leadingSpace = (number < 0) ? "" : " ";
        String format = leadingSpace + "%0" + width + "." + precision + "f  ";

        String str = String.format(format, number);

        str = convertLeadingZeros(number, str);

        assert str.length() == ((precision == 0) ? (addressNumber + THREE) : (addressNumber + modifier + FOUR));

        return str;
    }

    private String formatFloat(float number) {
        String leadingSpace = (number < 0) ? "" : " ";
        String format = leadingSpace + "%12.6e  ";

        String str = String.format(format, number);

        assert str.length() == FIFTEEN;

        return str;
    }

}

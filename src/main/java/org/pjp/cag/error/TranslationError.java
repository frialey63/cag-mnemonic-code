package org.pjp.cag.error;

/**
 * This enum represents the translation error numbers for the CAG computer.
 * @author developer
 *
 */
public enum TranslationError {

    /**
     * The error 1.  (May be due to a mis-punched tape.  The tape stops when this error is found.)
     */
    ERR_1(1, "Inadmissable character - no decode"),

    /**
     * The error 2.
     */
    ERR_2(2, "Unacceptable character in this position.\nCharacter other than letter, digit + -, (or) found."),

    /**
     * The error 3.  (Integer exceeding 131071)
     */
    ERR_3(3, "Number out of range."),

    /**
     * The error 4.
     *
     */
    ERR_4(4, "Compiling program out of store range.\nDue to omission of (STORE) directive."),

    /**
     * The error 5.
     */
    ERR_5(5, "Error in directive letters."),

    /**
     * The error 6.
     */
    ERR_6(6, "Integer not allowed in directive | Title string does not begin on newline."),

    /**
     * The error 7.
     */
    ERR_7(7, "Modifier not Index Register 0 - 9."),

    /**
     * The error 8.
     */
    ERR_8(8, "Error in Menomic letters."),

    /**
     * The error 9.
     */
    ERR_9(9, "Location address, or literal, out of range during loading.");

    private final int number;

    private final String description;

    TranslationError(int number, String description) {
        this.number = number;
        this.description = description;
    }


    public int number() {
        return number;
    }

    public String description() {
        return description;
    }

}

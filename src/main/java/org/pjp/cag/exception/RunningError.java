package org.pjp.cag.exception;

/**
 * This enum represents the run error numbers for the CAG computer.
 * @author developer
 *
 */
public enum RunningError {

    /**
     * The error 10.
     */
    ERR_10(10, "Jump to location out of store range."),

    /**
     * The error 11.
     */
    ERR_11(11, "Program obeying number not instruction."),

    /**
     * The error 12.
     */
    ERR_12(12, "Contents of modifier not a number."),

    /**
     * The error 13.
     */
    ERR_13(13, "Location address out of range during running."),

    /**
     * The error 14.
     */
    ERR_14(14, "Card instruction."),

    /**
     * The error 15.
     */
    ERR_15(15, "Arithmetic or Output attempted with instruction not number."),

    /**
     * The error 16.  (Due to PCT using location not filled by RCT instruction)
     */
    ERR_16(16, "Inadmissable character to output."),

    /**
     * The error 17.
     */
    ERR_17(17, "RUN before EXECUTE directive e.g. if RUN is given at a (WAIT)."),   // not supported

    /**
     * The error 18.
     */
    ERR_18(18, "Floating point overflow (e.g. dividing by zero).");

    private final int number;

    private final String description;

    RunningError(int number, String description) {
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

package org.pjp.cag.order;

import org.pjp.cag.instruction.Instruction;

/**
 * This enum represents the Function (mnemonic) constituent part of an Order.
 * @author developer
 *
 */
public enum Function {

    // CHECKSTYLE:OFF missing javadoc

    /*
     * Group 0
     */
    LDA(0),
    ADD(1),
    SUB(2),
    MLT(3),
    DIV(4),

    /*
     * Group 1
     */
    LDAN(10),
    ADDN(11),
    SUBN(12),
    MLTN(13),
    DIVN(14),

    /*
     * Group 2 (Store)
     */
    STA(20),

    /*
     * Group 3 (Jumps)
     */
    JUN(30),
    JGR(31),
    JEQ(32),
    JSR(33),
    JST(34),

    /*
     * Group 4 (Math)
     */
    SQT(40),
    EXP(41),
    LGN(42),
    SIN(43),
    COS(44),
    ARC(45),
    ENT(46),

    /*
     * Group 5 (Paper Tape)
     */
    RCT(50),
    PCT(51),
    RNT(52),
    PNT(53),    // both integral and fractional digits required?
    PNL(54);

    /*
     * Group 6 (Card) Not included
     */

    // CHECKSTYLE:ON

    private static final int ORIGINAL = 1964;

    private static final int BASE = 10;

    private final int code;

    private final int revision;

    Function(int code) {
        this.code = code;
        this.revision = ORIGINAL;
    }

    public int getRevision() {
        return revision;
    }

    /**
     * @return The group
     */
    public int group() {
        return code / BASE;
    }

    /**
     * @return The class through which the instruction is instantiated at interpretation time
     */
    public String instructionClass() {
        return String.format("%s.group%1d.%s", Instruction.class.getPackage().getName(), group(), name());
    }

    @Override
    public String toString() {
        return String.format("%02d", code);
    }

}
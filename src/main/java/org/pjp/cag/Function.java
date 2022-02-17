package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

/**
 * This enum represents the function (mnemonic) constituent part of an Order.
 * @author developer
 *
 */
enum Function {

    /*
     * Group 0
     */
    LDA(0, 2),
    ADD(1, 2),
    SUB(2, 2),
    MLT(3, 2),
    DIV(4, 2),

    /*
     * Group 1
     */
    LDAN(10, 1),
    ADDN(11, 1),
    SUBN(12, 1),
    MLTN(13, 1),
    DIVN(14, 1),

    /*
     * Group 2 (Store)
     */
    STA(20, 2),

    /*
     * Group 3 (Jumps)
     */
    JUN(30, 2),
    JGR(31, 2),
    JEQ(32, 2),
    JSR(33, 2),
    JST(34, 2),

    /*
     * Group 4 (Math)
     */
    SQT(40, 2),
    EXP(41, 2),
    LGN(42, 2),
    SIN(43),
    COS(44),
    ARC(45),
    ENT(46),

    /*
     * Group 5 (Paper Tape)
     */
    RCT(50, 2),
    PCT(51, 2),
    RNT(52),    // TODO address with maybe modifier will be ignored but must obey tests for error 7 and 9
    PNT(53, 2), // TODO spec for both integral and fractional digits required
    PNL(54);    // TODO address with maybe modifier will be ignored but must obey tests for error 7 and 9

    /*
     * Group 6 (Card) Not included
     */

    private static final int BASE = 10;

    private final int code;

    // where 0 means no args, 1 means exactly 1 arg, 2 means 1 or 2 args
    private final int arity;

    /**
     * @param code The instruction opcode
     * @param arity The arity, ie.e the expected number of arguments
     */
    Function(int code, int arity) {
        this.code = code;
        this.arity = arity;
    }

    /**
     * @param code The instruction opcode
     */
    Function(int code) {
        this(code, 0);
    }

    int group() {
        return code / BASE;
    }

    /**
     * @return The arity
     */
    int arity() {
        return arity;
    }

    /**
     * @return The class through which the instruction is instantiated at interpretation time
     */
    String instructionClass() {
        return Instruction.class.getPackage().getName() + "." + name();
    }

    @Override
    public String toString() {
        return String.format("%02d", code);
    }

}
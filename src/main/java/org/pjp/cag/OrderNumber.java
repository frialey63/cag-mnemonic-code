package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

/**
 * TODO maybe rename to Function or Mnemonic
 * This enum represents the mnemonic for the instruction corresponding to an Order.
 * @author developer
 *
 */
enum OrderNumber {

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
    RNT(52, 2),
    PNT(53, 2),
    PNL(54);

    /*
     * Group 6 (Card) Not included
     */

    private final int numericFunction;

    // where 0 means no args, 1 means exactly 1 arg, 2 means 1 or 2 args
    private final int arity;

    /**
     * @param numericFunction The instruction opcode
     * @param arity The arity, ie.e the expected number of arguments
     */
    OrderNumber(int numericFunction, int arity) {
        this.numericFunction = numericFunction;
        this.arity = arity;
    }

    /**
     * @param numericFunction The instruction opcode
     */
    OrderNumber(int numericFunction) {
        this(numericFunction, 0);
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
        return String.format("%02d", numericFunction);
    }

}
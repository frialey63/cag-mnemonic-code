package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

/**
 * This enum represents the mnemonic for the instruction corresponding to an Order.
 * @author developer
 *
 */
enum OrderNumber {
    LDA(0, 2),
    ADD(1, 2),
    SUB(2, 2),
    MLT(3, 2),
    DIV(4, 2),

    LDAN(10, 1),
    ADDN(11, 1),
    SUBN(12, 1),
    MLTN(13, 1),
    DIVN(14, 1),

    // Store
    STA(20, 2),

    // Jumps
    JUN(30, 2),
    JGR(31, 2),
    JEQ(32, 2),
    JSR(33, 2),
    JST(34),

    // Math TODO check the use of modifiers for error handlers
    SQT(40, 1),
    EXP(41, 1),
    LGN(42, 1),
    SIN(43, 1),
    COS(44, 1),
    ARC(45, 1),
    ENT(46, 1),

    // IO Read & Punch
    RCT(50, 2),
    PCT(51, 2),
    RNT(52, 2),
    PNT(53, 2),
    PNL(54);

    private final int numericFunction;

    private final int arity;    // could be derived by instruction lookup but that does not feel right for the assembly phase

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
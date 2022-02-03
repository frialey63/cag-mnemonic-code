package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

/**
 * This enum represents the mnemonic for the instruction corresponding to an Order.
 * @author developer
 *
 */
enum OrderNumber {
    LDA(0, 2),
    LDAN(10, 1),
    MLTN(13, 1),

    // Jumps
    JST(38, 0),

    // Math
    ARC(45, 1),

    // Input & Output
    PNT(53, 2);

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
     * @return The instruction opcode
     */
    int numericFunction() {
        return numericFunction;
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
}
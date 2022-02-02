package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

/**
 * This enum represents the mnemonic for the instruction corresponding to an Order.
 * @author developer
 *
 */
enum OrderNumber {
    LDA(0),
    LDAN(10),
    MLTN(13),

    // Jumps
    JST(38),

    // Math
    ARC(45),

    // Input & Output
    PNT(53);

    private final int numericFunction;

    /**
     * @param numericFunction The instruction opcode
     */
    OrderNumber(int numericFunction) {
        this.numericFunction = numericFunction;
    }

    /**
     * @return The instruction opcode
     */
    int numericFunction() {
        return numericFunction;
    }

    /**
     * @return The class through which the instruction is instantiated at interpretation time
     */
    String instructionClass() {
        return Instruction.class.getPackage().getName() + "." + name();
    }
}
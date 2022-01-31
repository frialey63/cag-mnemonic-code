package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

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

    OrderNumber(int numericFunction) {
        this.numericFunction = numericFunction;
    }

    public int numericFunction() {
        return numericFunction;
    }

    public String instructionClass() {
        return Instruction.class.getPackage().getName() + "." + name();
    }
}
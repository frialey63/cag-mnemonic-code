package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

enum OrderNumber {
    ARC(45),
    JST(38),
    LDAN(10),
    MLTN(13),
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
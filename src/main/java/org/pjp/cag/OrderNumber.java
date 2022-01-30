package org.pjp.cag;

import org.pjp.cag.instruction.Instruction;

enum OrderNumber {
    ARC,
    JST,
    LDAN,
    MLTN,
    PNT;

    public String instructionClass() {
        return Instruction.class.getPackage().getName() + "." + name();
    }
}
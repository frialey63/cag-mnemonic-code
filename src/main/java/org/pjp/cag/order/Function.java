package org.pjp.cag.order;

import org.pjp.cag.CAGMnemonicCode;
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
    JGR(31, 36),
    JEQ(32, 31),
    JNE(null, 32),
    JLE(null, 33),
    JGE(null, 34),
    JLT(null, 35),
    JSR(33, 37),
    JST(34, 38),
    LOP(null, 39),

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
     * Group 5 (Paper Tape, revised)
     */
    RCT(50, null),
    PCT(51, null),
    RNT(52, null),
    PNT(53, null),    // both integral and fractional digits required?
    PNL(54, null);

    /*
     * Group 6 (Card, revised) Not included
     */

    // CHECKSTYLE:ON

    private static final int BASE = 10;

    private final Integer code;

    private final Integer revisedCode;

    Function(int code) {
        this(code, code);
    }

    Function(Integer code, Integer revisedCode) {
        this.code = code;
        this.revisedCode = revisedCode;
    }

    Integer code() {
        return code;
    }

    Integer revisedCode() {
        return revisedCode;
    }

    /**
     * @return The code taking into account any revision.
     */
    int getCode() {
        return CAGMnemonicCode.isRevised() ? revisedCode : code;
    }

    /**
     * @return The group
     */
    int getGroup() {
        return getCode() / BASE;
    }

    /**
     * @return The class through which the instruction is instantiated at interpretation time
     */
    public String getInstructionClass() {
        return String.format("%s.group%1d.%s", Instruction.class.getPackage().getName(), getGroup(), name());
    }

    @Override
    public String toString() {
        return String.format("%02d", getCode());
    }

}
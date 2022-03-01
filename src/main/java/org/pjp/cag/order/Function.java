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
    LDAN(10, 10),

    ADDN(11, 11),
    SUBN(12, 12),
    MLTN(13, 13),
    DIVN(14, 14),

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
    PNL(54, null),

    ARD(null, 50),
    AWD(null, 51),
    RNA(null, 52),
    WNA(null, 53),

    /*
     * Group 6 (Card, revised) Not included
     */

    RCC(60, null),
    PCC(61, null),
    RNC(62, null),
    PNC(63, null),

    RCH(null, 60),
    WCH(null, 61),
    RNB(null, 62),
    WNB(null, 63),
    WNL(null, 64),
    WSS(null, 65),
    CNN(null, 66),
    CNC(null, 67),

    /*
     * Group 7
     */

    ACB(null, 70),
    BSP(null, 71),
    RWD(null, 72),

    /*
     * Group 9
     */

    STOP(null, 99);

    // CHECKSTYLE:ON

    private static final int BASE = 10;

    private final Integer code;

    private final Integer revisedCode;

    Function(int code) {
        this(code, null);
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
    Integer getCode() {
        return (CAGMnemonicCode.isRevised() && (revisedCode != null)) ? revisedCode : code;
    }

    /**
     * @return The group
     */
    Integer getGroup() {
        Integer code = getCode();

        return (code != null) ? (code / BASE) : null;
    }

    /**
     * FIXME the jumps are renumbered in the revised language but their executions are unchanged
     * @return The class through which the instruction is instantiated at interpretation time
     */
    public String getInstructionClass() {
        if (CAGMnemonicCode.isRevised()) {
            if (revisedCode != null) {
                return String.format("%s.group%1d.rev.%s", Instruction.class.getPackage().getName(), getGroup(), name());
            } else if (code != null) {
                return String.format("%s.group%1d.%s", Instruction.class.getPackage().getName(), getGroup(), name());
            }
        } else {
            if (code != null) {
                return String.format("%s.group%1d.%s", Instruction.class.getPackage().getName(), getGroup(), name());
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("%02d", getCode());
    }

}
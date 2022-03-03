package org.pjp.cag.cpu;

import static com.google.common.base.Preconditions.checkArgument;

import org.pjp.cag.exception.internal.IllegalLocationException;
import org.pjp.cag.exception.internal.IllegalRegisterException;

/**
 * The storage of the computer comprising 10 registers and 1000 words, each word can be populated by an Instruction, number or character.
 * @author developer
 *
 */
public final class Store {

    /**
     * The size of the store, i.e. number of words.
     */
    public static final int SIZE = 1000;

    /**
     * The number of registers.
     */
    public static final int REGISTERS = 10;

    /**
     * The constant for zero.
     */
    public static final int ZERO = 0;

    /**
     * The accumulator.
     */
    public static final int ACCUMULATOR = 1;

    /**
     * Index register 2.
     */
    public static final int INDEX_2 = 2;

    /**
     * Index register 3.
     */
    public static final int INDEX_3 = 3;

    /**
     * The link register.
     */
    public static final int LINK = 4;

    /**
     * Index register 5.
     */
    public static final int INDEX_5 = 5;

    /**
     * Index register 6.
     */
    public static final int INDEX_6 = 6;

    /**
     * Index register 7.
     */
    public static final int INDEX_7 = 7;

    /**
     * Index register 8.
     */
    public static final int INDEX_8 = 8;

    /**
     * Index register 9.
     */
    public static final int INDEX_9 = 9;

    private static final String DUMP_BEGIN = "------ STORE -----";

    private static final String DUMP_END =   "------------------";

    private Word[] word = new Word[SIZE];

    private final Accumulator accumulator = new Accumulator(this);

    private final ControlRegister controlRegister = new ControlRegister();

    {
        word[ZERO] = Word.create(0);

        for (int i = 1; i < REGISTERS; i++) {
            word[i] = Word.create(0);
        }

        for (int i = REGISTERS; i < SIZE; i++) {
            word[i] = Word.empty();
        }
    }

    public Accumulator accumulator() {
        return accumulator;
    }

    public ControlRegister controlRegister() {
        return controlRegister;
    }

    /**
     * @return True if Zero
     */
    public boolean zero() {
        return word[ZERO].number() == 0;
    }

    /**
     * Set the link address which enables the return from a subroutine.
     */
    public void updateLinkAddress() {
        int address = controlRegister().getAddress();

        setRegister(LINK, address + 1);
    }

    /**
     * @param register The register to set
     * @param number The number
     */
    public void setRegister(int register, float number) {
        checkArgument(register >= 0);

        if (register >= REGISTERS) {
            throw new IllegalRegisterException("illegal register: " + register);
        }

        if (register != ZERO) {
            this.word[register] = Word.create(number);
        }
    }

    /**
     * @param register The register to clear
     */
    public void clearRegister(int register) {
        setRegister(register, 0);
    }

    /**
     * @param register The register to get
     * @return The value of the register
     */
    public float getRegister(int register) {
        checkArgument(register >= 0);

        if (register >= REGISTERS) {
            throw new IllegalRegisterException("illegal register: " + register);
        }

        return word[register].number();
    }

    /**
     * @param location The location to set
     * @param word The word
     */
    public void setLocation(int location, Word word) {
        checkArgument(location >= 0);

        if (location >= SIZE) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        if (location != ZERO) {
            this.word[location] = word;
        }
    }

    /**
     * @param location The location to clear
     */
    public void clearLocation(int location) {
        setLocation(location, Word.empty());
    }

    /**
     * @param location The location to get
     * @return The word at the location
     */
    public Word getLocation(int location) {
        checkArgument(location >= 0);

        if (location >= SIZE) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        return word[location];
    }

    /**
     * @return Entire store content as a String
     */
    public String dump() {
        StringBuilder builder = new StringBuilder(DUMP_BEGIN);
        builder.append('\n');

        for (int i = 0; i < word.length; i++) {
            builder.append(String.format("%3d %s \n", i, word[i]));
        }

        builder.append(DUMP_END);

        return builder.toString();
    }
}

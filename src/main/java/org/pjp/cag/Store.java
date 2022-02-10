package org.pjp.cag;

import static com.google.common.base.Preconditions.checkArgument;

import org.pjp.cag.exception.IllegalLocationException;
import org.pjp.cag.exception.IllegalRegisterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The storage of the computer comprising 10 registers and 1000 words, each word can be populated by an Order, number or character.
 * @author developer
 *
 */
public final class Store {

    private static final String SEPARATOR = "------ STORE -----";

    private static final Logger LOGGER = LoggerFactory.getLogger(Store.class);

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
     * Index register 2.
     */
    public static final int INDEX_2 = 2;

    /**
     * Index register 3.
     */
    public static final int INDEX_3 = 3;

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

    /**
     * The link register.
     */
    public static final int LINK = 4;

    private static final int ACCUMULATOR = 1;

    private static final int CONTROL = 5;

    private Word[] word = new Word[SIZE];

    {
        word[ZERO] = Word.create(ZERO);

        for (int i = 1; i < REGISTERS; i++) {
            word[i] = Word.create(ZERO);
        }

        for (int i = REGISTERS; i < SIZE; i++) {
            word[i] = Word.empty();
        }
    }

    /**
     * @return True if Zero
     */
    public boolean zero() {
        return word[0].number() == ZERO;
    }

    /**
     * @param number The number
     */
    public void setAccumulator(float number) {
        setRegister(ACCUMULATOR, number);
    }

    /**
     * Clear the accumulator.
     */
    public void clearAccumulator() {
        clearRegister(ACCUMULATOR);
    }

    /**
     * @return The value of the accumulator
     */
    public float getAccumulator() {
        return getRegister(ACCUMULATOR);
    }

    /**
     * @param address The address
     */
    public void setControlAddress(int address) {
        checkArgument(address >= 0);

        if ((address > ZERO && address < REGISTERS) || (address >= SIZE)) {
            throw new IllegalLocationException("control address out of range: " + address);
        }

        setRegister(CONTROL, address);
    }

    /**
     * @return The address in the control register
     */
    public int getControlAddress() {
        return Math.round(getRegister(CONTROL));
    }

    /**
     * Increment the address in the control register.
     */
    public void incControlAddress() {
        setControlAddress(getControlAddress() + 1);
    }

    /**
     * Set the link address which enables the return from a subroutine.
     */
    public void updateLinkAddress() {
        int address = getControlAddress();

        setRegister(LINK, address + 1);
    }

    /**
     * @param register The register to set
     * @param number The number
     */
    public void setRegister(int register, float number) {
        checkArgument(register >= 0);

        if ((register == ZERO) || (register >= REGISTERS)) {
            throw new IllegalRegisterException("illegal register: " + register);
        }

        this.word[register] = Word.create(number);
    }

    /**
     * @param register The register to clear
     */
    public void clearRegister(int register) {
        setRegister(register, ZERO);
    }

    /**
     * @param register The register to get
     * @return The value of the register
     */
    public float getRegister(int register) {
        checkArgument(register >= 0);

        if ((register == ZERO) || (register >= REGISTERS)) {
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

        if ((location == ZERO) || (location >= SIZE)) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        this.word[location] = word;
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

        if ((location == ZERO) || (location >= SIZE)) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        return word[location];
    }

    /**
     * Dump the contents of the store.
     */
    public void dump() {
        StringBuilder builder = new StringBuilder(SEPARATOR);
        builder.append('\n');

        for (int i = 0; i < word.length; i++) {
            builder.append(String.format("%3d %s \n", i, word[i]));
        }

        builder.append(SEPARATOR);

        LOGGER.debug(builder.toString());
    }
}

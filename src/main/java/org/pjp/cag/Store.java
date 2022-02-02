package org.pjp.cag;

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

    private static final int ACCUMULATOR = 1;

    private static final int RETURN = 4;

    private static final int CONTROL = 5;

    private Word[] word = new Word[SIZE];

    {
        for (int i = 0; i < SIZE; i++) {
            word[i] = Word.empty();
        }
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
        if ((address > ZERO && address < REGISTERS) || address >= SIZE) {
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
     * @param register The register to set
     * @param number The number
     */
    public void setRegister(int register, float number) {
        if (register < 1 || register >= REGISTERS) {
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
        if (register < 1 || register >= REGISTERS) {
            throw new IllegalRegisterException("illegal register: " + register);
        }

        return word[register].number();
    }

    /**
     * @param location The location to set
     * @param word The word
     */
    public void setLocation(int location, Word word) {
        if (location < REGISTERS || location >= SIZE) {
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
        if (location < REGISTERS || location >= SIZE) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        return word[location];
    }

    /**
     * Dump the contents of the store.
     */
    public void dump() {
        for (int i = 0; i < word.length; i++) {
            LOGGER.debug(String.format("%3d %s \n", i, word[i]));
        }
    }
}

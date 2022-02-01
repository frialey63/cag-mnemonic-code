package org.pjp.cag;

import org.pjp.cag.exception.IllegalLocationException;
import org.pjp.cag.exception.IllegalRegisterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        for (int i = 0; i < word.length; i++) {
            word[i] = Word.empty();
        }
    }

    public void setAccumulator(float number) {
        setRegister(ACCUMULATOR, number);
    }

    public void clearAccumulator() {
        clearRegister(ACCUMULATOR);
    }

    public float getAccumulator() {
        return getRegister(ACCUMULATOR);
    }

    public void setControlAddress(int address) {
        if ((address > ZERO && address < REGISTERS) || address >= SIZE) {
            throw new IllegalLocationException("control address out of range: " + address);
        }

        setRegister(CONTROL, address);
    }

    public int getControlAddress() {
        return Math.round(getRegister(CONTROL));
    }

    public void incControlAddress() {
        setControlAddress(getControlAddress() + 1);
    }

    public void setRegister(int register, float number) {
        if (register < 1 || register >= REGISTERS) {
            throw new IllegalRegisterException("illegal register: " + register);
        }

        this.word[register] = Word.create(number);
    }

    public void clearRegister(int register) {
        setRegister(register, ZERO);
    }

    public float getRegister(int register) {
        if (register < 1 || register >= REGISTERS) {
            throw new IllegalRegisterException("illegal register: " + register);
        }

        return word[register].number();
    }

    public void setLocation(int location, Word word) {
        if (location < REGISTERS || location >= SIZE) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        this.word[location] = word;
    }

    public void clearLocation(int location) {
        setLocation(location, Word.empty());
    }

    public Word getLocation(int location) {
        if (location < REGISTERS || location >= SIZE) {
            throw new IllegalLocationException("location out of range: " + location);
        }

        return word[location];
    }

    public void dump() {
        for (int i = 0; i < word.length; i++) {
            LOGGER.debug(String.format("%3d %s \n", i, word[i]));
        }
    }
}

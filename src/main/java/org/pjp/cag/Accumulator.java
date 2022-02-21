package org.pjp.cag;

/**
 * The accumulator which performs the basic arithmetic operations.
 * @author developer
 *
 */
public final class Accumulator {

    private final Store store;

    /**
     * @param store The store
     */
    public Accumulator(Store store) {
        super();
        this.store = store;
    }

    public float get() {
        return store.getRegister(Store.ACCUMULATOR);
    }

    public void set(float number) {
        store.setRegister(Store.ACCUMULATOR, number);
    }

    /**
     * Clear the accumulator.
     */
    public void clear() {
        store.clearRegister(Store.ACCUMULATOR);
    }

    /**
     * @param value Add to the accumulator
     */
    public void add(float value) {
        set(get() + value);
    }

    /**
     * @param value Subtract from the accumulator
     */
    public void sub(float value) {
        set(get() - value);
    }

    /**
     * @param value Multiplier for the accumulator
     */
    public void mlt(float value) {
        set(get() * value);
    }

    /**
     * @param value Divisor for the accumulator
     */
    public void div(float value) {
        set(get() / value);
    }

}

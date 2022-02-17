package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * This abstract class represents the common elements for all instructions.
 * @author developer
 *
 */
public abstract class Instruction implements Executable {

    private boolean query;

    private int addressNumber;  // 0 - 999

    private int modifier;       // 0 - 9

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public Instruction(boolean query, int address, int modifier) {
        super();
        this.query = query;
        this.addressNumber = address;
        this.modifier = modifier;
    }

    /**
     * @param query The query flag
     * @param number The address or could be a constant
     */
    public Instruction(boolean query, int number) {
        this(query, number, 0);
    }

    /**
     * @param query The query flag
     */
    public Instruction(boolean query) {
        this(query, 0, 0);
    }

    /**
     * @return True if query
     */
    public boolean isQuery() {
        return query;
    }

    /**
     * @return The address/number as a number
     */
    protected int getNumber() {
        return addressNumber;
    }

    /**
     * @return The address as an address
     */
    protected int getAddress() {
        return addressNumber;
    }

    /**
     * @return The modifier
     */
    protected int getModifier() {
        return modifier;
    }

    /**
     * @param store The store
     * @return The effective address which is the summation of the address and the modification
     */
    protected int getEffectiveAddress(Store store) {
        return addressNumber + getModification(store);
    }


    private int getModification(Store store) {
        if (modifier == 0) {
            return 0;
        }

        return Math.round(store.getRegister(modifier));
    }
}

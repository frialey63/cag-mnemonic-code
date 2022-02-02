package org.pjp.cag.instruction;

import org.pjp.cag.Store;

/**
 * This abstract class represents the common elements for all instructions.
 * @author developer
 *
 */
public abstract class Instruction implements Executable {

    // CHECKSTYLE:OFF encapsulation

    boolean query;

    int addressNumber;  // 0 - 999

    int modifier;       // 0 - 9

    // CHECKSTYLE:ON

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
     * @param store The store
     * @return The effective address which is the summation of the address and the modification
     */
    int getEffectiveAddress(Store store) {
        return addressNumber + getModification(store);
    }


    private int getModification(Store store) {
        if (modifier == 0) {
            return 0;
        }

        return Math.round(store.getRegister(modifier));
    }
}

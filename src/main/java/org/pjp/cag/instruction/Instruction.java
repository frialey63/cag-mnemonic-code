package org.pjp.cag.instruction;

import static org.pjp.cag.Store.ZERO;

import org.pjp.cag.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.exception.internal.FaultyWordException;

/**
 * This abstract class represents the common elements for all instructions.
 * @author developer
 *
 */
public abstract class Instruction implements Executable {

    private boolean query;

    private int addressLiteral; // 0 - 999

    private int modifier;       // 0 - 9

    /**
     * @param query The query flag
     * @param address The address
     * @param modifier The modifier
     */
    public Instruction(boolean query, int address, int modifier) {
        super();
        this.query = query;
        this.addressLiteral = address;
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
     * @return The literal
     */
    protected int getLiteral() {
        return addressLiteral;
    }

    /**
     * @return The address
     */
    protected int getAddress() {
        return addressLiteral;
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
        return addressLiteral + getModification(store);
    }


    /**
     * @param store The store
     * @return The modification
     */
    protected int getModification(Store store) {
        if (modifier == ZERO) {
            return 0;
        }

        float modification;

        try {
            modification = store.getRegister(modifier);
        } catch (FaultyWordException e) {
            throw new RunningException(RunningError.ERR_12);
        }

        return Math.round(modification);
    }
}

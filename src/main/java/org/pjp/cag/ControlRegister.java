package org.pjp.cag;

import static com.google.common.base.Preconditions.checkArgument;

import org.pjp.cag.exception.internal.IllegalLocationException;

/**
 * The control register which holds the address of the next instruction to be executed..
 * @author developer
 *
 */
public final class ControlRegister {

    private int address;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        checkArgument(address >= 0);

        if ((address > Store.ZERO && address < Store.REGISTERS) || (address >= Store.SIZE)) {
            throw new IllegalLocationException("control address out of range: " + address);
        }

        this.address = address;
    }

    /**
     * Increment the address.
     */
    public void incAddress() {
        setAddress(getAddress() + 1);
    }

}

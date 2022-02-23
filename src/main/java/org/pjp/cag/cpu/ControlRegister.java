package org.pjp.cag.cpu;

import static com.google.common.base.Preconditions.checkArgument;

import org.pjp.cag.exception.internal.IllegalLocationException;

/**
 * The Control Register which holds the address of the next instruction to be executed.
 * @author developer
 *
 */
public final class ControlRegister {

    private int address;

    private boolean wait;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        checkArgument(address >= 0);

        if (address >= Store.SIZE) {
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

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

}

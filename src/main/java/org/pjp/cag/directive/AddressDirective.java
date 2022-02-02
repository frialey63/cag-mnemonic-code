package org.pjp.cag.directive;

import java.util.Arrays;

import org.pjp.cag.Store;

import com.google.common.base.Preconditions;

/**
 * This class represents a directive with an associated address.
 * @author developer
 *
 */
public final class AddressDirective extends Directive {

    /**
     * The STORE directive.
     */
    public static final String STORE = "STORE";

    /**
     * The EXECUTE directive.
     */
    public static final String EXECUTE = "EXECUTE";

    private static final String[] TYPES = {EXECUTE, STORE };   // natural ordering

    private final int address;

    /**
     * @param type The type of the directive
     * @param address The address
     */
    public AddressDirective(String type, int address) {
        super(type);
        this.address = address;

        Preconditions.checkArgument(Arrays.binarySearch(TYPES, type) != -1, "illegal type");
        Preconditions.checkArgument((Store.REGISTERS <= address)  && (address < Store.SIZE), "illegal type");
    }

    /**
     * @return The address
     */
    public int getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "AddressDirective [address=" + address + ", getType()=" + getType() + "]";
    }

}

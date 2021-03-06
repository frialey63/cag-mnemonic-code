package org.pjp.cag.order;

import static com.google.common.base.Preconditions.checkNotNull;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.TranslationError;
import org.pjp.cag.exception.TranslationException;

/**
 * This class represents a Directive with an associated address.
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

    private final int address;

    /**
     * @param type The type of the directive
     * @param address The address
     * @throws TranslationException
     */
    public AddressDirective(String type, int address) throws TranslationException {
        super(type);
        this.address = checkNotNull(address, "address cannot be null");

        if (address >= Store.SIZE) {
            throw new TranslationException(TranslationError.ERR_9);
        }
    }

    /**
     * @return The address
     */
    public int address() {
        return address;
    }

    @Override
    public String toString() {
        return "AddressDirective [address=" + address + ", type()=" + type() + "]";
    }

}

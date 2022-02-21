package org.pjp.cag;

import static com.google.common.base.Preconditions.checkNotNull;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.TranslationError;
import org.pjp.cag.exception.TranslationException;

/**
 * The Order comprises the mnemonic for an instruction together with its associated arguments.
 * @author developer
 *
 */
public final class Order {

    static final int NULL = -1;

    /**
     * Note PNT n,m will be accepted with any feasible n and m, there is no special validation for PNT.
     *
     * @param query If true then provide trace output when executed
     * @param functionStr The mnemonic for the instruction
     * @param addressStr The address
     * @param modifierStr The modifier
     * @return The order
     * @throws TranslationException
     */
    public static Order create(boolean query, String functionStr, String addressStr, String modifierStr) throws TranslationException {
        try {
            Function function = Function.valueOf(functionStr);

            if (addressStr == null) {
                // only for test
                return new Order(function);
            } else {
                int address = Integer.parseInt(addressStr);          // will parse because matched to number in the regex

                if (address >= Store.SIZE) {
                    throw new TranslationException(TranslationError.ERR_9);
                }

               if (modifierStr == null) {
                    return new Order(query, function, address);
                } else {
                    int modifier = Integer.parseInt(modifierStr);   // will parse because matched to number in the regex

                    if (modifier >= Store.REGISTERS) {
                        throw new TranslationException(TranslationError.ERR_7);
                    }

                    return new Order(query, function, address, modifier);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new TranslationException(TranslationError.ERR_8);
        }
    }

    // CHECKSTYLE:OFF encapsulation

    final boolean query;

    final Function function;

    final int address;  // 10 - 999

    final int modifier; // 0 - 9

    // CHECKSTYLE:ON

    private Order(boolean query, Function function, int address, int modifier) {
        super();
        this.query = query;
        this.function = checkNotNull(function, "function cannot be null");
        this.address = address;
        this.modifier = modifier;
    }

    private Order(boolean query, Function function, int address) {
        this(query, function, address, NULL);
    }

    private Order(Function function) {
        this(false, function, NULL, NULL);
    }

    /**
     * @return True if the modifier is present
     */
    boolean hasModifier() {
        return modifier != NULL;
    }

    /**
     * @return True if the address is present
     */
    boolean hasAddress() {
        return address != NULL;
    }

    @Override
    public String toString() {
        int queryFlag = query ? 1 : 0;

        if (hasModifier()) {
            return String.format("%s%1d%03d%1d", function, queryFlag, address, modifier);
        } else if (hasAddress()) {
            return String.format("%s%1d%03d0", function, queryFlag, address);
        }

        return String.format("%s%1d0000", function, queryFlag);
     }

    // CHECKSTYLE:OFF auto-generated

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + address;
        result = prime * result + modifier;
        result = prime * result + ((function == null) ? 0 : function.hashCode());
        result = prime * result + (query ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (address != other.address)
            return false;
        if (modifier != other.modifier)
            return false;
        if (function != other.function)
            return false;
        if (query != other.query)
            return false;
        return true;
    }

    // CHECKSTYLE:ON

}

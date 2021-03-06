package org.pjp.cag.order;

import static com.google.common.base.Preconditions.checkNotNull;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.TranslationError;
import org.pjp.cag.exception.TranslationException;

/**
 * The Instruction comprises the mnemonic for a function together with its associated arguments, i.e address and modifier.
 * @author developer
 *
 */
public final class Instruction {

    static final int NULL = -1;

    /**
     * Note PNT n,m will be accepted with any feasible n and m, there is no special validation for PNT.
     *
     * @param query If true then provide trace output when executed
     * @param functionStr The mnemonic for the instruction
     * @param addressStr The address
     * @param modifierStr The modifier
     * @return The instruction
     * @throws TranslationException
     */
    public static Instruction create(boolean query, String functionStr, String addressStr, String modifierStr) throws TranslationException {
        try {
            Function function = Function.valueOf(functionStr);

            if (addressStr == null) {
                return new Instruction(function);
            } else {
                int address = Integer.parseInt(addressStr);          // will parse because matched to number in the regex

                if (address >= Store.SIZE) {
                    throw new TranslationException(TranslationError.ERR_9);
                }

               if (modifierStr == null) {
                    return new Instruction(query, function, address);
                } else {
                    int modifier = Integer.parseInt(modifierStr);   // will parse because matched to number in the regex

                    if (modifier >= Store.REGISTERS) {
                        throw new TranslationException(TranslationError.ERR_7);
                    }

                    return new Instruction(query, function, address, modifier);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new TranslationException(TranslationError.ERR_8);
        }
    }

    private final boolean query;

    private final Function function;

    private final int address;  // 10 - 999

    private final int modifier; // 0 - 9

    private Instruction(boolean query, Function function, int address, int modifier) {
        super();
        this.query = query;
        this.function = checkNotNull(function, "function cannot be null");
        this.address = address;
        this.modifier = modifier;
    }

    private Instruction(boolean query, Function function, int address) {
        this(query, function, address, NULL);
    }

    private Instruction(Function function) {
        this(false, function, NULL, NULL);
    }

    public boolean query() {
        return query;
    }

    public Function function() {
        return function;
    }

    public int address() {
        return address;
    }

    public int modifier() {
        return modifier;
    }

    /**
     * @return True if the modifier is present
     */
    public boolean hasModifier() {
        return modifier != NULL;
    }

    /**
     * @return True if the address is present
     */
    public boolean hasAddress() {
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
        Instruction other = (Instruction) obj;
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

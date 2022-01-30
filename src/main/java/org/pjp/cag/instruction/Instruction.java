package org.pjp.cag.instruction;

import org.pjp.cag.Store;

public abstract class Instruction implements Executable {

    // CHECKSTYLE:OFF encapsulation

    boolean query;

    int addressNumber;  // 0 - 999

    int modifier;       // 0 - 9

    // CHECKSTYLE:ON

    public Instruction(boolean query, int address, int modifier) {
        super();
        this.query = query;
        this.addressNumber = address;
        this.modifier = modifier;
    }

    public Instruction(boolean query, int number) {
        this(query, number, 0);
    }

    public Instruction(boolean query) {
        this(query, 0, 0);
    }

    private int getModification(Store store) {
        if (modifier == 0) {
            return 0;
        }

        return Math.round(store.getRegister(modifier));
    }

    int getEffectiveAddress(Store store) {
        return addressNumber + getModification(store);
    }

}

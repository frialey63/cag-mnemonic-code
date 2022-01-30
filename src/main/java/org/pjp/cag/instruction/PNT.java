package org.pjp.cag.instruction;

import org.pjp.cag.Store;

public final class PNT extends Instruction {

    public PNT(boolean query, int integralDigits, int fractionalDigits) {
        super(query, integralDigits, fractionalDigits);
    }

    @Override
    public boolean execute(Store store) {
        float accumulator = store.getAccumulator();

        int width = addressNumber + modifier;
        int precision = modifier;

        String format = "%" + width + "." + precision + "f";

        System.out.printf(format, accumulator);

        return true;
    }

}

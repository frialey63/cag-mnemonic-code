package org.pjp.cag.instruction.group3;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stop, originally Wait with facility to continue from the modifiable address
 * when CONTINUE button pressed.
 *
 * @author developer
 *
 */
public final class JST extends Instruction {

    private static final Logger LOGGER = LoggerFactory.getLogger(JST.class);

    /**
     * @param query    The query flag
     * @param address  The address
     * @param modifier The modifier
     */
    public JST(boolean query, int address, int modifier) {
        super(query, address, modifier);
    }

    @Override
    public boolean execute(Store store) {
        int effectiveAddress = getEffectiveAddress(store);

        if (effectiveAddress >= Store.SIZE) {
            throw new RunningException(RunningError.ERR_10);
        }

        if (effectiveAddress != Store.ZERO) {
            LOGGER.info("stopped! no support for program continuation after wait; legacy multi-part input tapes must be presented as a single file");
        }

        store.controlRegister().setAddress(effectiveAddress);

        store.controlRegister().setWait(true);

        return false;
    }

}

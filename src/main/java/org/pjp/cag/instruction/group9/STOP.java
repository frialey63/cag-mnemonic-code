package org.pjp.cag.instruction.group9;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.instruction.Instruction;

/**
 * Return control to the operating system.
 *
 * @author developer
 *
 */
public final class STOP extends Instruction {

    /**
     * Default constructor.
     */
    public STOP() {
        super();
    }

    @Override
    public boolean execute(Store store) {
        store.controlRegister().setStop(true);

        return true;
    }

}

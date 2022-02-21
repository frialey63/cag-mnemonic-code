package org.pjp.cag.instruction;

import org.pjp.cag.cpu.Store;

/**
 * This interface represents the executability of any instruction.
 * @author developer
 *
 */
public interface Executable {

    /**
     * @param store The store
     * @return True if the control register should be incremented following execution
     */
    boolean execute(Store store);
}

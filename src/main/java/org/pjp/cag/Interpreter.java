package org.pjp.cag;

import static org.pjp.cag.Store.ZERO;

import java.lang.reflect.Constructor;

import org.pjp.cag.exception.AbstractCAGException;
import org.pjp.cag.instruction.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Interpreter reads the Orders from the store according to the control register and instantiates and executes the corresponding instruction.
 * @author developer
 *
 */
final class Interpreter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interpreter.class);

    /**
     * @param store The computer store
     * @param trace If true then produce trace
     */
    void interpret(Store store, boolean trace) {
        try {
            while (true) {
                int address = ZERO;

                try {
                    address = store.getControlAddress();

                    if (address == ZERO) {
                        break;
                    }

                    Order order = store.getLocation(address).order();

                    if (trace && order.query) {
                        System.out.printf("Q %4d %.6e\n", store.getControlAddress(), store.getAccumulator());
                    }

                    String instructionClassName = order.orderNumber.instructionClass();

                    Class<?> clazz = Class.forName(instructionClassName);

                    Instruction instruction;

                    if (order.hasModifier()) {
                        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address, order.modifier);
                    } else if (order.hasAddress()) {
                        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address);
                    } else {
                        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(boolean.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query);
                    }

                    if (instruction.execute(store)) {
                        store.incControlAddress();
                    }
                } catch (AbstractCAGException e) {
                    System.out.printf("ERR %2d %4d\n", e.getErrorCode(), address);
                    LOGGER.debug(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("caught unexpected Exception while interpreting the program", e);
        }
    }
}

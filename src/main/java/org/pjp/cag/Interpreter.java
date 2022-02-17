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
        int address = ZERO;

        try {
            while (true) {
                address = store.getControlAddress();

                if (address == ZERO) {
                    break;
                }

                Order order = store.getLocation(address).order();

                String instructionClassName = order.function.instructionClass();

                Class<?> clazz = Class.forName(instructionClassName);
                Constructor<?> declaredConstructor = null;

                Instruction instruction;

                if (order.hasModifier()) {
                    declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                    instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address, order.modifier);
                } else if (order.hasAddress()) {
                    try {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address);
                    } catch (NoSuchMethodException e) {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address, ZERO);
                    }
                } else {
                    try {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query);
                    } catch (NoSuchMethodException e) {
                        try {
                            declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                            instruction = (Instruction) declaredConstructor.newInstance(order.query, ZERO);
                        } catch (Exception e1) {
                            declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                            instruction = (Instruction) declaredConstructor.newInstance(order.query, ZERO, ZERO);
                        }
                    }
                }

                int savedAddress = address;

                if (instruction.execute(store)) {
                    store.incControlAddress();
                }

                if (trace && instruction.isQuery()) {
                    System.out.printf("Q %4d %.6e\n", savedAddress, store.getAccumulator());
                }

            }
        } catch (AbstractCAGException e) {
            System.out.printf("ERR %2d %4d\n", e.getErrorCode(), address);
            LOGGER.debug(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("caught unexpected Exception while interpreting the program", e);
        }
    }
}

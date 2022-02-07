package org.pjp.cag;

import static org.pjp.cag.Store.ZERO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.pjp.cag.instruction.Instruction;
import org.pjp.cag.io.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The interpreter reads the Orders from the store according to the control register and instantiates and executes the corresponding instruction.
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

        while (true) {
            int address = store.getControlAddress();

            if (address == ZERO) {
                break;
            }

            Order order = store.getLocation(address).order();

            if (trace && order.query) {
                PaperTape.out.printf("Q %4d %.6e\n", store.getControlAddress(), store.getAccumulator());
            }

            String instructionClassName = order.orderNumber.instructionClass();

            try {
                Class<?> clazz = Class.forName(instructionClassName);

                try {
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

                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("Caught Exception while attempting to instantiate instruction", e);
                } catch (NoSuchMethodException e) {
                    LOGGER.error("Caught Exception while attempting to get constructor for instruction", e);
                }
            } catch (SecurityException | ClassNotFoundException e) {
                LOGGER.error("Caught exception while looking up instruction class", e);
            }
        }
    }
}

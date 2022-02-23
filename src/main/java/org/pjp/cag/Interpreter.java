package org.pjp.cag;

import static org.pjp.cag.cpu.Store.ZERO;

import java.lang.reflect.Constructor;

import org.pjp.cag.cpu.ControlRegister;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.exception.internal.FaultyWordException;
import org.pjp.cag.exception.internal.IllegalLocationException;
import org.pjp.cag.instruction.Instruction;
import org.pjp.cag.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Interpreter reads the Orders from the store according to the control register and instantiates and executes the corresponding instruction.
 * @author developer
 *
 */
final class Interpreter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interpreter.class);

    static Order getOrder(Store store, int address) {
        try {
            return store.getLocation(address).order();
        } catch (IllegalLocationException e) {
            throw new RunningException(RunningError.ERR_13);
        } catch (FaultyWordException e) {
            throw new RunningException(RunningError.ERR_11);
        }
    }

    static boolean executeInstruction(Store store, Instruction instruction) {
        try {
            return instruction.execute(store);
        } catch (IllegalLocationException e) {
            throw new RunningException(RunningError.ERR_13);
        } catch (ArithmeticException e) {
            throw new RunningException(RunningError.ERR_18);
        }
    }

    /**
     * @param store The computer store
     * @param trace If true then produce trace
     */
    void interpret(Store store, boolean trace) {
        int address = ZERO;

        try {
            while (true) {
                ControlRegister controlRegister = store.controlRegister();

                if (controlRegister.isWait()) {
                    break;
                }

                address = controlRegister.getAddress();

                Order order = getOrder(store, address);

                String instructionClassName = order.function().instructionClass();

                Class<?> clazz = Class.forName(instructionClassName);
                Constructor<?> declaredConstructor = null;

                Instruction instruction;

                if (order.hasModifier()) {
                    declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                    instruction = (Instruction) declaredConstructor.newInstance(order.query(), order.address(), order.modifier());
                } else if (order.hasAddress()) {
                    try {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query(), order.address());
                    } catch (NoSuchMethodException e) {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query(), order.address(), ZERO);
                    }
                } else {
                    try {
                        // for completeness, there are no instructions with this constructor
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class);
                        instruction = (Instruction) declaredConstructor.newInstance(order.query());
                    } catch (NoSuchMethodException e) {
                        try {
                            declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                            instruction = (Instruction) declaredConstructor.newInstance(order.query(), ZERO);
                        } catch (NoSuchMethodException e1) {
                            declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                            instruction = (Instruction) declaredConstructor.newInstance(order.query(), ZERO, ZERO);
                        }
                    }
                }

                int savedAddress = address;

                if (executeInstruction(store, instruction)) {
                    controlRegister.incAddress();
                }

                if (trace && instruction.isQuery()) {
                    System.out.printf("Q %4d %.6e\n", savedAddress, store.accumulator().get());
                }
            }
        } catch (RunningException e) {
            System.out.printf("ERR %s %4d\n", e.getMessage(), address);
            LOGGER.debug(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("caught unexpected Exception while interpreting the program", e);
        }
    }

}

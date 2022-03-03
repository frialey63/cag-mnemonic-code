package org.pjp.cag;

import static org.pjp.cag.cpu.Store.ZERO;

import java.lang.reflect.Constructor;

import org.pjp.cag.cpu.ControlRegister;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.exception.internal.FaultyWordException;
import org.pjp.cag.exception.internal.IllegalLocationException;
import org.pjp.cag.instruction.MachineInstruction;
import org.pjp.cag.order.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Interpreter reads the Instructions from the store according to the control register and instantiates and executes the corresponding instruction.
 * @author developer
 *
 */
final class Interpreter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interpreter.class);

    static Instruction getInstruction(Store store, int address) {
        try {
            return store.getLocation(address).instruction();
        } catch (IllegalLocationException e) {
            throw new RunningException(RunningError.ERR_13);
        } catch (FaultyWordException e) {
            throw new RunningException(RunningError.ERR_11);
        }
    }

    static boolean executeMachineInstruction(Store store, MachineInstruction instruction) {
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

                Instruction instruction = getInstruction(store, address);

                String machineInstructionClassName = instruction.function().machineInstructionClassName();

                Class<?> clazz = Class.forName(machineInstructionClassName);
                Constructor<?> declaredConstructor = null;

                MachineInstruction machineInstruction;

                if (instruction.hasModifier()) {
                    declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                    machineInstruction = (MachineInstruction) declaredConstructor.newInstance(instruction.query(), instruction.address(), instruction.modifier());
                } else if (instruction.hasAddress()) {
                    try {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                        machineInstruction = (MachineInstruction) declaredConstructor.newInstance(instruction.query(), instruction.address());
                    } catch (NoSuchMethodException e) {
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                        machineInstruction = (MachineInstruction) declaredConstructor.newInstance(instruction.query(), instruction.address(), ZERO);
                    }
                } else {
                    try {
                        // for completeness, there are no instructions with this constructor
                        declaredConstructor = clazz.getDeclaredConstructor(boolean.class);
                        machineInstruction = (MachineInstruction) declaredConstructor.newInstance(instruction.query());
                    } catch (NoSuchMethodException e) {
                        try {
                            declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class);
                            machineInstruction = (MachineInstruction) declaredConstructor.newInstance(instruction.query(), ZERO);
                        } catch (NoSuchMethodException e1) {
                            declaredConstructor = clazz.getDeclaredConstructor(boolean.class, int.class, int.class);
                            machineInstruction = (MachineInstruction) declaredConstructor.newInstance(instruction.query(), ZERO, ZERO);
                        }
                    }
                }

                int savedAddress = address;

                if (executeMachineInstruction(store, machineInstruction)) {
                    controlRegister.incAddress();
                }

                if (trace && machineInstruction.isQuery()) {
                    System.out.printf("Q %4d %.6e\n", savedAddress, store.accumulator().get());
                }
            }
        } catch (RunningException e) {
            System.out.printf(e.getMessage(), address);
        } catch (Exception e) {
            LOGGER.error("caught unexpected Exception while interpreting the program", e);
        }
    }

}

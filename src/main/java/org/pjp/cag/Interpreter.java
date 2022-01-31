package org.pjp.cag;

import static org.pjp.cag.Store.ZERO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.pjp.cag.instruction.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Interpreter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interpreter.class);

    private static final int THREE = 3;

    public void interpret(Store store) {

        while (true) {
            int address = store.getControlAddress();

            if (address == ZERO) {
                break;
            }

            Order order = store.getLocation(address).order();

            String instructionClassName = order.orderNumber.instructionClass();

            try {
                Class<?> clazz = Class.forName(instructionClassName);

                Constructor<?> declaredConstructor = clazz.getDeclaredConstructors()[0];
                int parameterCount = declaredConstructor.getParameterCount();

                try {
                    Instruction instruction;

                    switch (parameterCount) {
                    case 1:
                        instruction = (Instruction) declaredConstructor.newInstance(order.query);
                        break;
                    case 2:
                        instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address);
                        break;
                    case THREE:
                        instruction = (Instruction) declaredConstructor.newInstance(order.query, order.address, order.modifier);
                        break;
                    default:
                        throw new IllegalStateException();
                    }

                    if (instruction.execute(store)) {
                        store.incControlAddress();
                    }

                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("Caught Exception while attempting to instantiate instruction", e);
                }
            } catch (SecurityException | ClassNotFoundException e) {
                LOGGER.error("Caught exception while looking up instruction class", e);
            }
        }
    }
}

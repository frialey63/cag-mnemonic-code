package org.pjp.cag.cpu;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.exception.internal.IllegalLocationException;

public class ControlRegisterTest {

    @Test
    public void testGetAddress() {
        ControlRegister register = new ControlRegister();

        assertEquals(ZERO, register.getAddress());
    }

    @Test
    public void testSetAddress() {
        ControlRegister register = new ControlRegister();

        register.setAddress(567);

        assertEquals(567, register.getAddress());

        int maxAddress = Store.SIZE - 1;

        register.setAddress(maxAddress);

        assertEquals(maxAddress, register.getAddress());
    }

    @Test
    public void testIncAddress() {
        ControlRegister register = new ControlRegister();

        register.setAddress(567);

        assertEquals(567, register.getAddress());

        register.incAddress();

        assertEquals(568, register.getAddress());
    }

    @Test
    public void testSetControlAddressMax() {
        ControlRegister register = new ControlRegister();

        int address = Store.SIZE - 1;

        register.setAddress(address);
    }

    @Test
    public void testSetControlAddressMin() {
        ControlRegister register = new ControlRegister();

        int address = Store.REGISTERS;

        register.setAddress(address);
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetControlAddressIllegalMax() {
        ControlRegister register = new ControlRegister();

        register.setAddress(Store.SIZE);
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetControlAddressIllegalMin() {
        ControlRegister register = new ControlRegister();

        register.setAddress(Store.REGISTERS - 1);
    }

}

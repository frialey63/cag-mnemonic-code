package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.IllegalLocationException;
import org.pjp.cag.exception.IllegalRegisterException;

public class StoreTest {

    @Test
    public void testZero() {
        Store store = new Store();

        assertTrue(store.zero());
    }

    @Test
    public void testSetAccumulator() {
        Store store = new Store();

        store.setAccumulator(321);

        assertEquals(321, store.getAccumulator(), TestConstants.PRECISION);

        store.clearAccumulator();

        assertEquals(0, store.getAccumulator(), TestConstants.PRECISION);
    }

    @Test
    public void testSetControlAddress() {
        Store store = new Store();

        store.setControlAddress(567);

        assertEquals(567, store.getControlAddress());

        store.incControlAddress();

        assertEquals(568, store.getControlAddress());

        int maxAddress = Store.SIZE - 1;

        store.setControlAddress(maxAddress);

        assertEquals(maxAddress, store.getControlAddress());
    }

    @Test
    public void testSetControlAddressMax() {
        Store store = new Store();

        int address = Store.SIZE - 1;

        store.setControlAddress(address);
   }

    @Test
    public void testSetControlAddressMin() {
        Store store = new Store();

        int address = Store.REGISTERS;

        store.setControlAddress(address);
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetControlAddressIllegalMax() {
        Store store = new Store();

        store.setControlAddress(Store.SIZE);
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetControlAddressIllegalMin() {
        Store store = new Store();

        store.setControlAddress(Store.REGISTERS - 1);
    }

    @Test
    public void testSetRegister() {
        Store store = new Store();

        int register = 3;

        store.setRegister(register, 248);

        assertEquals(248, store.getRegister(register), TestConstants.PRECISION);

        store.clearRegister(register);

        assertEquals(0, store.getRegister(register), TestConstants.PRECISION);
    }

    @Test
    public void testSetRegisterMax() {
        Store store = new Store();

        int register = Store.REGISTERS - 1;

        store.setRegister(register, 248);

        store.getRegister(register);
    }

    @Test
    public void testSetRegisterMin() {
        Store store = new Store();

        int register = 1;

        store.setRegister(register, 248);

        store.getRegister(register);
    }

    @Test(expected = IllegalRegisterException.class)
    public void testSetRegisterIllegalMax() {
        Store store = new Store();

        store.setRegister(Store.REGISTERS, 123);
    }

    @Test(expected = IllegalRegisterException.class)
    public void testGetRegisterIllegalMax() {
        Store store = new Store();

        store.getRegister(Store.REGISTERS);
    }

    @Test(expected = IllegalRegisterException.class)
    public void testSetRegisterIllegalMin() {
        Store store = new Store();

        store.setRegister(Store.ZERO, 123);
    }

    @Test(expected = IllegalRegisterException.class)
    public void testGetRegisterIllegalMin() {
        Store store = new Store();

        store.getRegister(Store.ZERO);
    }

    @Test
    public void testSetLocation() {
        Store store = new Store();

        int address = 333;

        store.setLocation(address, Word.create(3579));

        assertEquals(3579, store.getLocation(address).number(), TestConstants.PRECISION);

        store.clearLocation(address);

        assertTrue(store.getLocation(address).isEmpty());
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetLocationIllegalMax() {
        Store store = new Store();

        store.setLocation(Store.SIZE, Word.create(3579));
    }

    @Test(expected = IllegalLocationException.class)
    public void testGetLocationIllegalMax() {
        Store store = new Store();

        store.getLocation(Store.SIZE);
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetLocationIllegalMin() {
        Store store = new Store();

        store.setLocation(Store.ZERO, Word.create(3579));
    }

    @Test(expected = IllegalLocationException.class)
    public void testGetLocationIllegalMin() {
        Store store = new Store();

        store.getLocation(Store.ZERO);
    }

}

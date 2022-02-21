package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;
import org.pjp.cag.exception.internal.IllegalLocationException;
import org.pjp.cag.exception.internal.IllegalRegisterException;
import org.pjp.cag.test.TestConstants;

public class StoreTest {

    @Test
    public void testDump() throws TranslationException {
        Store store = new Store();
        store.setLocation(12, Word.create(Order.create(false, "LDA", "123", "3")));
        store.setLocation(100, Word.create(123));
        store.setLocation(200, Word.create('A'));

        store.dump();
    }

    @Test
    public void testZero() {
        Store store = new Store();

        assertTrue(store.zero());
    }

    @Test
    public void testSetAccumulator() {
        Store store = new Store();

        store.setAccumulator(321);

        assertEquals(321.0f, store.getAccumulator(), TestConstants.DELTA);

        store.clearAccumulator();

        assertEquals(0.0f, store.getAccumulator(), TestConstants.DELTA);
    }

    @Test
    public void testSetControlAddress() {
        Store store = new Store();

        store.setControlAddress(567);

        assertEquals(567, store.getControlAddress());

        int maxAddress = Store.SIZE - 1;

        store.setControlAddress(maxAddress);

        assertEquals(maxAddress, store.getControlAddress());
    }

    @Test
    public void testIncControlAddress() {
        Store store = new Store();

        store.setControlAddress(567);

        assertEquals(567, store.getControlAddress());

        store.incControlAddress();

        assertEquals(568, store.getControlAddress());
    }

    @Test
    public void testSetLinkAddress() {
        Store store = new Store();

        store.setControlAddress(567);

        store.updateLinkAddress();

        assertEquals(568, (int) store.getRegister(Store.LINK));
    }

    @Test
    public void testSetRegister() {
        Store store = new Store();

        int register = 3;

        store.setRegister(register, 248);

        assertEquals(248.0f, store.getRegister(register), TestConstants.DELTA);

        store.clearRegister(register);

        assertEquals(0f, store.getRegister(register), TestConstants.DELTA);
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
    public void testSetRegisterIllegalMin() {
        Store store = new Store();

        store.setRegister(Store.ZERO, 123);
    }

    @Test(expected = IllegalRegisterException.class)
    public void testGetRegisterIllegalMax() {
        Store store = new Store();

        store.getRegister(Store.REGISTERS);
    }

    @Test
    public void testSetLocation() {
        Store store = new Store();

        int address = 333;

        store.setLocation(address, Word.create(3579));

        assertEquals(3579.0f, store.getLocation(address).number(), TestConstants.DELTA);

        store.clearLocation(address);

        assertTrue(store.getLocation(address).isEmpty());
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetLocationIllegalMax() {
        Store store = new Store();

        store.setLocation(Store.SIZE, Word.create(3579));
    }

    @Test(expected = IllegalLocationException.class)
    public void testSetLocationIllegalMin() {
        Store store = new Store();

        store.setLocation(Store.ZERO, Word.create(3579));
    }

    @Test(expected = IllegalLocationException.class)
    public void testGetLocationIllegalMax() {
        Store store = new Store();

        store.getLocation(Store.SIZE);
    }


}

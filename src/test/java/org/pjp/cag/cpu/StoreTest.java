package org.pjp.cag.cpu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;
import org.pjp.cag.exception.internal.IllegalLocationException;
import org.pjp.cag.exception.internal.IllegalRegisterException;
import org.pjp.cag.order.Instruction;
import org.pjp.cag.test.TestConstants;

public class StoreTest {

    @Test
    public void testDump() throws TranslationException {
        Store store = new Store();
        store.setLocation(12, Word.create(Instruction.create(false, "LDA", "123", "3")));
        store.setLocation(100, Word.create(123));
        store.setLocation(200, Word.create('A'));

        store.dump();
    }

    @Test
    public void testZero() {
        Store store = new Store();

        assertTrue(store.zero());

        store.setRegister(Store.ZERO, 123);

        assertTrue(store.zero());

        store.setLocation(Store.ZERO, Word.create(3579));

        assertTrue(store.zero());
    }

    @Test
    public void testSetLinkAddress() {
        Store store = new Store();

        store.controlRegister().setAddress(567);

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

        assertEquals(0, store.getRegister(register), TestConstants.DELTA);
    }

    @Test
    public void testSetRegisterMax() {
        Store store = new Store();

        int register = Store.REGISTERS - 1;

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

    @Test
    public void testSetLocation() {
        Store store = new Store();

        int address = 333;

        store.setLocation(address, Word.create(3579));

        assertEquals(3579.0f, store.getLocation(address).number(), TestConstants.DELTA);

        store.clearLocation(address);

        assertTrue(store.getLocation(address).isEmpty());
    }

    @Test
    public void testSetLocationMax() {
        Store store = new Store();

        int address = Store.SIZE - 1;

        store.setLocation(address, Word.create(3579));

        store.getLocation(address);
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


}

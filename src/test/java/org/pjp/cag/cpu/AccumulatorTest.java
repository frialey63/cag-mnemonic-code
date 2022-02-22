package org.pjp.cag.cpu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.test.TestConstants;

public class AccumulatorTest {

    @Test
    public void testGet() {
        Store store = new Store();
        store.setRegister(Store.ACCUMULATOR, 321);

        assertEquals(321.0f, store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testSet() {
        Store store = new Store();

        store.accumulator().set(321);

        assertEquals(321.0f, store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testClear() {
        Store store = new Store();
        store.setRegister(Store.ACCUMULATOR, 321);

        store.accumulator().clear();

        assertEquals(0, store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testAdd() {
        Store store = new Store();

        store.accumulator().set(123);
        store.accumulator().add(456);

        assertEquals((float) (123 + 456), store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testSub() {
        Store store = new Store();

        store.accumulator().set(123);
        store.accumulator().sub(456);

        assertEquals((float) (123 - 456), store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testMlt() {
        Store store = new Store();

        store.accumulator().set(123);
        store.accumulator().mlt(456);

        assertEquals((float) (123 * 456), store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testDiv() {
        Store store = new Store();

        store.accumulator().set(123);
        store.accumulator().div(456);

        assertEquals((float) (123.0 / 456), store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testDivFloatingPointOverflow() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            store.accumulator().set(123);
            store.accumulator().div(0);
        });

        assertEquals(RunningError.ERR_18.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}

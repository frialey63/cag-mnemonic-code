package org.pjp.cag.directive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.exception.StorageException;

public class AddressDirectiveTest {

    private static final int ADDRESS_12 = 12;

    @Test(expected = StorageException.class)
    public void testConstructCannotStore() {
        new AddressDirective(AddressDirective.STORE, 9);
    }

    @Test(expected = StorageException.class)
    public void testConstructCannotExecute() {
        new AddressDirective(AddressDirective.EXECUTE, 1000);
    }

    @Test
    public void testGetAddress() {
        AddressDirective addressDirective = new AddressDirective(AddressDirective.STORE, ADDRESS_12);

        assertEquals(ADDRESS_12, addressDirective.getAddress());
    }

    @Test
    public void testGetType() {
        AddressDirective addressDirective = new AddressDirective(AddressDirective.STORE, ADDRESS_12);

        assertEquals(AddressDirective.STORE, addressDirective.getType());
    }

}

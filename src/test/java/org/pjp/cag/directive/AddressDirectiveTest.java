package org.pjp.cag.directive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddressDirectiveTest {

    private static final int ADDRESS_12 = 12;

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

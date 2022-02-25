package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;

public class AddressDirectiveTest {

    private static final int ADDRESS_12 = 12;

    @Test
    public void testAddress() throws TranslationException {
        AddressDirective addressDirective = new AddressDirective(AddressDirective.STORE, ADDRESS_12);

        assertEquals(ADDRESS_12, addressDirective.address());
    }

    @Test
    public void testType() throws TranslationException {
        AddressDirective addressDirective = new AddressDirective(AddressDirective.STORE, ADDRESS_12);

        assertEquals(AddressDirective.STORE, addressDirective.type());
    }

}

package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TitleDirectiveTest {

    private static final String SIMPLE_TEST = "SIMPLE TEST";

    @Test
    public void testGetTitle() {
        TitleDirective titleDirective = new TitleDirective(SIMPLE_TEST);

        assertEquals(SIMPLE_TEST, titleDirective.getTitle());
    }

    @Test
    public void testGetType() {
        TitleDirective titleDirective = new TitleDirective(SIMPLE_TEST);

        assertEquals(TitleDirective.TITLE, titleDirective.getType());
    }

}

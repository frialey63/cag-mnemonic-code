package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TitleDirectiveTest {

    private static final String SIMPLE_TEST = "SIMPLE TEST";

    @Test
    public void testTitle() {
        TitleDirective titleDirective = new TitleDirective(SIMPLE_TEST);

        assertEquals(SIMPLE_TEST, titleDirective.title());
    }

    @Test
    public void testType() {
        TitleDirective titleDirective = new TitleDirective(SIMPLE_TEST);

        assertEquals(TitleDirective.TITLE, titleDirective.type());
    }

}

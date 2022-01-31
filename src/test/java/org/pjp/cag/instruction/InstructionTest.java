package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class InstructionTest {

    @Test
    public void testGetEffectiveAddressUnmodified() {
        Store store = new Store();

        LDA lda = new LDA(false, 100, 0);

        assertEquals(100, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressZeroModifier() {
        Store store = new Store();
        store.clearRegister(3);

        LDA lda = new LDA(false, 100, 3);

        assertEquals(100, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressModified() {
        Store store = new Store();
        store.setRegister(3, 10);

        LDA lda = new LDA(false, 100, 3);

        assertEquals(110, lda.getEffectiveAddress(store));
    }

}

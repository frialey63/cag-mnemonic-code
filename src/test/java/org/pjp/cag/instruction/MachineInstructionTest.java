package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.cpu.Word;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.group0.LDA;
import org.pjp.cag.instruction.group1.LDAN;
import org.pjp.cag.instruction.group4.SQT;
import org.pjp.cag.order.Order;

public class MachineInstructionTest {

    @Test
    public void testGetLiteral() {
        LDAN ldan = new LDAN(false, 100);

        assertEquals(100, ldan.getLiteral());
    }

    @Test
    public void testGetAddress() {
        SQT sqt = new SQT(false, 100, ZERO);

        assertEquals(100, sqt.getAddress());
    }

    @Test
    public void testGetModifier() {
        LDA lda = new LDA(false, 100, 3);

        assertEquals(3, lda.getModifier());
    }

    @Test
    public void testIsQuery() {
        LDA lda = new LDA(true, 100, 3);

        assertTrue(lda.isQuery());
    }

    @Test
    public void testGetModificationWasOrder() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setLocation(3, Word.create(Order.create(false, "LDA", "123", "3")));

            LDA lda = new LDA(false, 100, 3);
            lda.getModification(store);
        });

        assertEquals(RunningError.ERR_12, exception.getError());
    }

    @Test
    public void testGetModificationWasCharacter() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setLocation(3, Word.create('A'));

            LDA lda = new LDA(false, 100, 3);
            lda.getModification(store);
        });

        assertEquals(RunningError.ERR_12, exception.getError());
    }

    @Test
    public void testGetEffectiveAddressUnmodified() {
        Store store = new Store();

        LDA lda = new LDA(false, 100, ZERO);

        assertEquals(100, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressZeroModifier() {
        Store store = new Store();

        LDA lda = new LDA(false, 100, 3);

        assertEquals(100, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressModifiedNearestPositive() {
        Store store = new Store();
        store.setRegister(3, 18.3f);

        LDA lda = new LDA(false, 100, 3);

        assertEquals(118, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressModifiedNearestNegative() {
        Store store = new Store();
        store.setRegister(3, -10.8f);

        LDA lda = new LDA(false, 100, 3);

        assertEquals(89, lda.getEffectiveAddress(store));
    }

}

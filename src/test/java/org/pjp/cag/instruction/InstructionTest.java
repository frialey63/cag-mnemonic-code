package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Order;
import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.instruction.group0.LDA;
import org.pjp.cag.instruction.group1.LDAN;
import org.pjp.cag.instruction.group4.SQT;

public class InstructionTest {

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
    public void testGetModificationWasOrder() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setLocation(3, Word.create(Order.create(false, "LDA", "123", "3")));

            LDA lda = new LDA(false, 100, 3);
            lda.getModification(store);
        });

        assertEquals(RunningError.ERR_12.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testGetModificationWasCharacter() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setLocation(3, Word.create('A'));

            LDA lda = new LDA(false, 100, 3);
            lda.getModification(store);
        });

        assertEquals(RunningError.ERR_12.number(), Integer.parseInt(exception.getMessage().trim()));
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
        store.clearRegister(3);

        LDA lda = new LDA(false, 100, 3);

        assertEquals(100, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressModifiedNearestPositive() {
        Store store = new Store();
        store.setRegister(3, 10.3f);

        LDA lda = new LDA(false, 100, 3);

        assertEquals(110, lda.getEffectiveAddress(store));
    }

    @Test
    public void testGetEffectiveAddressModifiedNearestNegative() {
        Store store = new Store();
        store.setRegister(3, -9.8f);

        LDA lda = new LDA(false, 120, 3);

        assertEquals(110, lda.getEffectiveAddress(store));
    }

}

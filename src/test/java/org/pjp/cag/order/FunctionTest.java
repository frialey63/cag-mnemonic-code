package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionTest {

    @Test
    public void testGroup() {

        assertEquals(0, Function.LDA.group());

        assertEquals(1, Function.ADDN.group());

        assertEquals(2, Function.STA.group());

        assertEquals(3, Function.JEQ.group());

        assertEquals(4, Function.SIN.group());

        assertEquals(5, Function.PNL.group());
    }

    @Test
    public void testMachineInstructionClassName() {
        assertEquals("org.pjp.cag.instruction.group0.LDA", Function.LDA.machineInstructionClassName());

        assertEquals("org.pjp.cag.instruction.group1.ADDN", Function.ADDN.machineInstructionClassName());

        assertEquals("org.pjp.cag.instruction.group2.STA", Function.STA.machineInstructionClassName());

        assertEquals("org.pjp.cag.instruction.group3.JEQ", Function.JEQ.machineInstructionClassName());

        assertEquals("org.pjp.cag.instruction.group4.SIN", Function.SIN.machineInstructionClassName());

        assertEquals("org.pjp.cag.instruction.group5.PNL", Function.PNL.machineInstructionClassName());
   }

    @Test
    public void testToString() {
        Function function = Function.LDAN;

        assertEquals("10", function.toString());
    }

}

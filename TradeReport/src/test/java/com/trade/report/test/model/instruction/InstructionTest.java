package com.trade.report.test.model.instruction;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.trade.report.model.instruction.Instruction;
import com.trade.report.model.instruction.TradeAction;

public class InstructionTest {

    @Test
    public void testTradeAmountCalc() throws Exception {
        final BigDecimal agreedFx = BigDecimal.valueOf(0.50);
        final BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        final int units = 200;

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20), // Its a Monday
                agreedFx,
                units,
                pricePerUnit);

        // test initialization
        assertEquals(agreedFx, fakeInstruction.getAgreedFx());
        assertEquals(pricePerUnit, fakeInstruction.getPricePerUnit());
        assertEquals(units, fakeInstruction.getUnits());

        final BigDecimal correct = pricePerUnit
                                    .multiply(agreedFx)
                                    .multiply(BigDecimal.valueOf(units))
                                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(correct, fakeInstruction.getTradeAmount());
    }
}
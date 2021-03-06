package com.trade.report.test.businesslogic;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.trade.report.businesslogic.CalculateInstructionSettlementDate;
import com.trade.report.model.instruction.Instruction;
import com.trade.report.model.instruction.TradeAction;

public class InstructionSettlementDateCalculatorTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SGD"), // Its Default
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        CalculateInstructionSettlementDate.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("USD"), // Its Default
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                BigDecimal.valueOf(1),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        CalculateInstructionSettlementDate.calculateSettlementDate(fakeInstruction);

        // should be the first monday (27/3/2017)
        assertEquals(LocalDate.of(2017, 3, 27), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("AED"), // Its Arabia (AED)
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        CalculateInstructionSettlementDate.calculateSettlementDate(fakeInstruction);

        // should be the first Sunday (26/3/2017)
        assertEquals(LocalDate.of(2017, 3, 26), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SAR"), // Its Arabia (SAR)
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        CalculateInstructionSettlementDate.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }
}
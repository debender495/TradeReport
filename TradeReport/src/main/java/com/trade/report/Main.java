package com.trade.report;

import java.util.Set;

import com.trade.report.businesslogic.ReportGenerator;
import com.trade.report.model.instruction.Instruction;
import com.trade.report.utils.DummyInstructionsGenerator;

public class Main {

    public static void main(String[] args) {
        final Set<Instruction> instructions = DummyInstructionsGenerator.getDummyInstructions();
        final ReportGenerator reportGenerator = new ReportGenerator();

        System.out.println(reportGenerator.generateInstructionsReport(instructions));
    }
}

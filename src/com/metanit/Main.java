package com.metanit;

import java.util.List;
import java.util.Locale;

public class Main {

    private static void runInCommandLineMode(String[] args) {
        InputArgs inputArgs = ArgParser.parseCmdArgs(args);
        TripleArg value = TaskManager.parseInputFile(inputArgs.getInput());
        List<Apartment> resultMap = Solution.findTheMostCheap(value.getList(), value.getN(), value.getMinS());
        TaskManager.writeToOutputFile(inputArgs.getOutput(), resultMap);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        if (args.length == 0) {
            new TableForm();
        } else {
            runInCommandLineMode(args);
        }
        testAll();
    }

    public static void testAll() {
        Tests.testCase1();
        Tests.testCase2();
        Tests.testCase3();
    }

}

package com.metanit;

public class ArgParser {

    public static InputArgs parseCmdArgs(String[] args) {

        String argName = null;
        InputArgs inputArgs = new InputArgs(args);
        for (String arg : args) {
            if (arg.startsWith("-") && arg.length() == 2 || arg.startsWith("--")) {
                argName = arg;
            } else {
                if (argName != null) {
                    if (argName.equals("-i") || argName.equals("--input-file")) {
                        inputArgs.setInput(arg);
                    } else if (argName.equals("-o") || argName.equals("--output-file")) {
                        inputArgs.setOutput(arg);
                    }
                }
            }
        }
        return inputArgs;
    }
}

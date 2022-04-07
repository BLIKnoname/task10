package com.metanit;

public class InputArgs {
    private String input;
    private String output;

    public InputArgs(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public InputArgs(String[] args) {
        this(args[0], args[1]);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}


package org.pasqg.planetgenerator.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArgumentsBuilder {
    private final Map<String, Object> mArguments;

    public ArgumentsBuilder() {
        mArguments = new LinkedHashMap<>();
    }

    public <T> ArgumentsBuilder addArgument(String aArgument, T aValue) {
        mArguments.put(aArgument, aValue);
        return this;
    }

    public List<String> toList(String aExecutable) {
        List<String> arguments = new ArrayList<>();
        arguments.add(aExecutable);
        for (var entry : mArguments.entrySet()) {
            arguments.add(entry.getKey());
            arguments.add(entry.getValue().toString());
        }
        return arguments;
    }

    public String toString(String aExecutable) {
        StringBuilder myStringBuilder = new StringBuilder(aExecutable);
        for (String element : toList(aExecutable)) {
            myStringBuilder.append(" ").append(element);
        }
        return myStringBuilder.toString();
    }
}

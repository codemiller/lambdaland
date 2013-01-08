package com.example;

import java.util.Map;

import static com.example.Option.asOption;

public class Example {

    public static int readPositiveIntParamWithoutOption(Map<String, String> params, String name) {
        String value = params.get(name);
        if (value == null) return 0;
        int i = 0;
        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
        }
        if (i < 0) return 0;
        return i;
    }

    public static int readPositiveIntParam(Map<String, String> params, String name) {
        return asOption(params.get(name))
                .flatMap(FunctionUtils::stringToInt)
                .filter(i -> i > 0)
                .getOrElse(0);
    }
}

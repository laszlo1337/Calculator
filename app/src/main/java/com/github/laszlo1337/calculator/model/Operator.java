package com.github.laszlo1337.calculator.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laszlo on 2017-03-21.
 */

public enum Operator {
    ADD(1), SUBTRACT(1), DIVIDE(2), MULTIPLY(2);
    final int precedence;

    Operator(int precedence) {
        this.precedence = precedence;
    }

    private static Map<String, Operator> operators = new HashMap<String, Operator>(){{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("÷", Operator.DIVIDE);
        put("×", Operator.MULTIPLY);
    }};

    public static Operator fromString(String token){
        return operators.get(token);
    }

    public static boolean isOperator(String token){
        return operators.containsKey(token);
    }

    public boolean hasHigherPrecedenceThan(String token){
        if (isOperator(token) && this.precedence >= fromString(token).precedence){
            return true;
        }
        return false;
    }
}

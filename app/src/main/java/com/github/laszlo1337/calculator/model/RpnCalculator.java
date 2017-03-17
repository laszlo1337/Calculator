package com.github.laszlo1337.calculator.model;

import java.util.Stack;

import static com.github.laszlo1337.calculator.model.Symbols.DIVISION;
import static com.github.laszlo1337.calculator.model.Symbols.LINE_BREAK;
import static com.github.laszlo1337.calculator.model.Symbols.MINUS;
import static com.github.laszlo1337.calculator.model.Symbols.MULTIPLICATION;
import static com.github.laszlo1337.calculator.model.Symbols.PLUS;

/**
 * Created by laszlo on 2017-03-12.
 */

public class RpnCalculator {


    public RpnCalculator() {

    }


    public float eval(float arg1, float arg2, String operator) {
        switch (operator) {
            case PLUS:
                return arg1 + arg2;
            case MINUS:
                return arg2 - arg1;
            case MULTIPLICATION:
                return arg1 * arg2;
            case DIVISION:
                return arg2 / arg1;
            default:
                return 0;
        }
    }

    public String evaluate(String operator, String expression) {
        Stack<String> stack = new Stack<>();
        String[] args = expression.split(LINE_BREAK);
        for (String s : args) {
            stack.push(s);
        }
        if (stack.size() < 2) {return expression;}
        StringBuilder resultExpression = new StringBuilder();

        float result = eval(Float.parseFloat(stack.pop()), Float.parseFloat(stack.pop()), operator);
        stack.push(format(result));

        String[] resultArgs = new String[stack.size()];
        stack.toArray(resultArgs);
        for (String s : resultArgs) {
            resultExpression.append(s + LINE_BREAK);
        }

        return resultExpression.toString();
    }

    private String format(final float f) {
        final int i = (int) f;
        if (f == i) {
            return Integer.toString(i);
        }
        return Float.toString(f);
    }

    // TODO: 2017-03-17 implement evaluation from infix expression
    public String evaluateInfixExpression(String expression) {
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < expression.length(); i++){
            if(isANumber(String.valueOf(expression.charAt(i)))){

            }
        }

        return expression;
    }

    public boolean isANumber(String number) {
        if (number.matches("\\d")) {
            return true;
        }
        return false;
    }

}

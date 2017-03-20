package com.github.laszlo1337.calculator.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Stack;

import static com.github.laszlo1337.calculator.model.Symbols.DIVISION;
import static com.github.laszlo1337.calculator.model.Symbols.LINE_BREAK;
import static com.github.laszlo1337.calculator.model.Symbols.MINUS;
import static com.github.laszlo1337.calculator.model.Symbols.MULTIPLICATION;
import static com.github.laszlo1337.calculator.model.Symbols.PLUS;
import static com.github.laszlo1337.calculator.model.Symbols.SPACE;

/**
 * Created by laszlo on 2017-03-12.
 */

public class RpnCalculator {

    private HashMap<String, Integer> precedence;

    public RpnCalculator() {
        precedence = new HashMap<>();
        precedence.put(PLUS, 1);
        precedence.put(MINUS, 1);
        precedence.put(MULTIPLICATION, 2);
        precedence.put(DIVISION, 2);
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
        String[] args = expression.split(LINE_BREAK);
        if (args.length < 2) {
            return expression;
        }

        Stack<String> stack = new Stack<>();
        for (String s : args) {
            stack.push(s);
        }

        StringBuilder resultExpression = new StringBuilder();
        float result = eval(Float.parseFloat(stack.pop()), Float.parseFloat(stack.pop()), operator);
        stack.push(format(result));

        String[] resultArgs = new String[stack.size()];
        stack.toArray(resultArgs);
        for (String s : resultArgs) {
            resultExpression.append(s);
            resultExpression.append(LINE_BREAK);
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
        Stack<String> operators = new Stack<>();
        String[] args = expression.split(SPACE);
        Stack<String> values = new Stack<>();

        for (String arg : args) {
            if (isANumber(arg)) {
                values.push(arg);
                continue;
            }
            if (operators.isEmpty()) {
                operators.push(arg);
            } else if (precedence.get(arg) < precedence.get(operators.peek())) {
                float result = eval(Float.parseFloat(values.pop()), Float.parseFloat(values.pop()), operators.pop());
                values.push(String.valueOf(result));
                operators.push(arg);
            } else if (precedence.get(arg) == precedence.get(operators.peek())) {
                float result = eval(Float.parseFloat(values.pop()), Float.parseFloat(values.pop()), operators.pop());
                values.push(String.valueOf(result));
                operators.push(arg);
            } else if (precedence.get(arg) > precedence.get(operators.peek())) {
                operators.push(arg);
            }
        }


        while (!operators.isEmpty()) {
            float result = eval(Float.parseFloat(values.pop()), Float.parseFloat(values.pop()), operators.pop());
            values.push(String.valueOf(result));
        }

        Log.d("RPN_CALCULATOR", "result =" + values.pop());


        return expression;
    }

    public boolean isANumber(String number) {
        if (number.matches("-?\\d+")) {
            return true;
        }

        return false;
    }

}

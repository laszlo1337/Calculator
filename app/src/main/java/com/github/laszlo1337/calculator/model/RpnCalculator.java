package com.github.laszlo1337.calculator.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import static com.github.laszlo1337.calculator.model.Helpers.LINE_BREAK;
import static com.github.laszlo1337.calculator.model.Helpers.SPACE;
import static com.github.laszlo1337.calculator.model.Operator.fromString;
import static com.github.laszlo1337.calculator.model.Operator.isOperator;

/**
 * Created by laszlo on 2017-03-12.
 */

public class RpnCalculator {

    public RpnCalculator() {

    }


    public String evaluate(String operator, String stringStack) {
        String[] args = stringStack.split(LINE_BREAK);
        if (args.length < 2) return stringStack;

        Deque<String> stack = new LinkedList<>();
        for (String s : args) stack.push(s);

        float result = eval(Float.parseFloat(stack.pop()), Float.parseFloat(stack.pop()), Operator.fromString(operator));
        stack.push(format(result));

        StringBuilder resultExpression = new StringBuilder();
        while (!stack.isEmpty()) {
            resultExpression.append(stack.removeLast());
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

    public String evaluateInfixExpression(String expression) {
        Stack<Float> operands = new Stack<>();
        Stack<Operator> operators = new Stack<>();

        for (String token : expression.split(SPACE)) {
            if (isOperator(token)) {
                while (!operators.isEmpty() && operators.peek().hasHigherPrecedenceThan(token)) {
                    operands.add(eval(operands.pop(), operands.pop(), operators.pop()));
                }
                operators.push(fromString(token));
            } else {
                operands.add(Float.valueOf(token));
            }
        }

        while (!operators.isEmpty()) {
            operands.add(eval(operands.pop(), operands.pop(), operators.pop()));
        }
        return format(operands.pop());
    }

    private Float eval(float arg2, float arg1, Operator operator) {
        switch (operator) {
            case ADD:
                return arg1 + arg2;
            case SUBTRACT:
                return arg1 - arg2;
            case MULTIPLY:
                return arg1 * arg2;
            case DIVIDE:
                return arg1 / arg2;
            default:
                return 0f;
        }
    }


}

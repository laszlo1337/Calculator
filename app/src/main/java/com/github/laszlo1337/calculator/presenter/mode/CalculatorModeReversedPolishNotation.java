package com.github.laszlo1337.calculator.presenter.mode;


import com.github.laszlo1337.calculator.model.RpnCalculator;

import java.util.Stack;


public class CalculatorModeReversedPolishNotation implements CalculatorMode {

    private final RpnCalculator rpnCalculator;
    private final String LINE_BREAK = "\n";

    public CalculatorModeReversedPolishNotation(RpnCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    @Override
    public String performOperatorAction(String operator, String expression) {
        // TODO: 2017-03-15 move this code to RpnCalculator class
        Stack<String> stack = new Stack<>();
        String[] args = expression.split("\n");
        for (String s : args) {
            stack.push(s);
        }
        if (stack.size() < 2) {
            return expression;
        }
        StringBuilder resultExpression = new StringBuilder();
        double result;
        if (operator.equals("+")) {
            double arg1 = Double.parseDouble(stack.pop());
            double arg2 = Double.parseDouble(stack.pop());
            result = arg1 + arg2;
            stack.push(format(result));
        } else if (operator.equals("-")) {
            double arg2 = Double.parseDouble(stack.pop());
            double arg1 = Double.parseDouble(stack.pop());
            result = arg1 - arg2;
            stack.push(format(result));
        } else if (operator.equals("*")) {
            double arg2 = Double.parseDouble(stack.pop());
            double arg1 = Double.parseDouble(stack.pop());
            result = arg1 * arg2;
            stack.push(format(result));
        } else if (operator.equals("/")) {
            double arg2 = Double.parseDouble(stack.pop());
            double arg1 = Double.parseDouble(stack.pop());
            result = arg1 / arg2;
            stack.push(format(result));
        }
        String[] resultArgs = new String[stack.size()];
        stack.toArray(resultArgs);

        for (String s : resultArgs) {
            resultExpression.append(s + LINE_BREAK);
        }

        return resultExpression.toString();
    }

    @Override
    public String performSettableButtonAction(String expression) {
        if (expression.endsWith(LINE_BREAK) || expression.isEmpty()) {
            return expression;
        }
        return expression.concat(LINE_BREAK);
    }

    private String format(final double d) {
        final int i = (int) d;
        if (d == i) {
            return Integer.toString(i);
        }
        return Double.toString(d);
    }


}

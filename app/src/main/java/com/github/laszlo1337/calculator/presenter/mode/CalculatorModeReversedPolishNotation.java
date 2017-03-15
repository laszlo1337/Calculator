package com.github.laszlo1337.calculator.presenter.mode;


import com.github.laszlo1337.calculator.model.RpnCalculator;

import java.util.Stack;


public class CalculatorModeReversedPolishNotation implements CalculatorMode {

    private final RpnCalculator rpnCalculator;
    private final String NEXT_LINE = "\n";

    public CalculatorModeReversedPolishNotation(RpnCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    @Override
    public String performOperatorAction(String operator, String expression) {
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
//            final int i = (int) f;
//            if (f == i) {
//                return Integer.toString(i);
//            }
//
//            return Double.toString(f);
            stack.push(Double.toString(result));
        } else if (operator.equals("-")) {
            double arg2 = Double.parseDouble(stack.pop());
            double arg1 = Double.parseDouble(stack.pop());

            result = arg1 - arg2;

            stack.push(Double.toString(result));
        } else if (operator.equals("*")) {
            double arg2 = Double.parseDouble(stack.pop());
            double arg1 = Double.parseDouble(stack.pop());

            result = arg1 * arg2;

            stack.push(Double.toString(result));
        } else if (operator.equals("/")) {
            double arg2 = Double.parseDouble(stack.pop());
            double arg1 = Double.parseDouble(stack.pop());

            result = arg1 / arg2;

            stack.push(Double.toString(result));
        }
        String[] resultArgs = new String[stack.size()];
        stack.toArray(resultArgs);

        for (String s : resultArgs) {
            resultExpression.append(s + NEXT_LINE);
        }

        return resultExpression.toString();
    }

    @Override
    public String performSettableButtonAction(String expression) {
        if (expression.endsWith("\n") || expression.isEmpty()) {
            return expression;
        }
        return expression.concat(NEXT_LINE);
    }


}

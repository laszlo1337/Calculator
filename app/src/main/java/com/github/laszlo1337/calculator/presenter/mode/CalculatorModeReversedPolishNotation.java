package com.github.laszlo1337.calculator.presenter.mode;


import com.github.laszlo1337.calculator.model.RpnCalculator;
import static com.github.laszlo1337.calculator.model.Symbols.LINE_BREAK;


public class CalculatorModeReversedPolishNotation implements CalculatorMode {

    private RpnCalculator rpnCalculator;

    public CalculatorModeReversedPolishNotation(RpnCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    @Override
    public String performOperatorAction(String operator, String expression) {
        return rpnCalculator.evaluate(operator, expression);
    }

    @Override
    public String performSettableButtonAction(String expression) {
        if (expression.endsWith(LINE_BREAK) || expression.isEmpty()) {
            return expression;
        }
        expression += LINE_BREAK;
        rpnCalculator.evaluateInfixExpression("-2 + 6 * 8 / 3 * 18 - 33 / 3 - 11");
        return expression;
    }




}

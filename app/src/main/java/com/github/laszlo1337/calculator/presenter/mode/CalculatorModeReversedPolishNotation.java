package com.github.laszlo1337.calculator.presenter.mode;


import com.github.laszlo1337.calculator.model.RpnCalculator;

import static com.github.laszlo1337.calculator.model.CalculatorModel.isADigit;
import static com.github.laszlo1337.calculator.model.Helpers.DECIMAL_POINT;
import static com.github.laszlo1337.calculator.model.Helpers.LINE_BREAK;


public class CalculatorModeReversedPolishNotation implements CalculatorMode {

    private RpnCalculator rpnCalculator;

    public CalculatorModeReversedPolishNotation() {
        this.rpnCalculator = new RpnCalculator();
    }

    @Override
    public String performOperatorAction(String operator, String expression) {
        if(isValid(expression)) {
            return rpnCalculator.evaluate(operator, expression);
        }
        return expression;
    }

    @Override
    public String performSettableButtonAction(String expression) {
        if (expression.endsWith(LINE_BREAK) || expression.isEmpty()) {
            return expression;
        }
        expression += LINE_BREAK;

        return expression;
    }

    @Override
    public String appendDecimal(String expression) {
        if (expression.isEmpty()) {
            return expression.concat("0.");
        }
        if(!isADigit(expression.substring(expression.length()-1))) return expression;
        if (expression.contains(LINE_BREAK) && !expression.substring(expression.lastIndexOf(LINE_BREAK), expression.length()-1).contains(DECIMAL_POINT)) {
            return expression.concat(DECIMAL_POINT);
        } else if (!expression.contains(DECIMAL_POINT)){
            return expression.concat(DECIMAL_POINT);
        }
        return expression;
    }

    @Override
    public String deleteCharacter(String expression) {
        if (expression.length() <= 1) {
            return "";
        }
        /**
         * if decimal is present, delete two characters. 3.1 becomes 3
         */
        if (expression.lastIndexOf(DECIMAL_POINT) == expression.length() - 2) {
            return expression.substring(0, expression.length() - 2);
        }
        return expression.substring(0, expression.length() - 1);
    }

    private boolean isValid(String exp){
        for(String arg : exp.split(LINE_BREAK)){
            if (!arg.matches("-?\\d+")){
                return false;
            }
        }
        return true;
    }
}

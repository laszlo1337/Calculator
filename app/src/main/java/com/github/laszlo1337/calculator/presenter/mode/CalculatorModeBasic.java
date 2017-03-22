package com.github.laszlo1337.calculator.presenter.mode;


import com.github.laszlo1337.calculator.model.RpnCalculator;

import static com.github.laszlo1337.calculator.model.CalculatorModel.isADigit;
import static com.github.laszlo1337.calculator.model.Helpers.DECIMAL_POINT;
import static com.github.laszlo1337.calculator.model.Helpers.SPACE;

public final class CalculatorModeBasic implements CalculatorMode {

    private final RpnCalculator rpnCalculator;

    public CalculatorModeBasic(RpnCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    @Override
    public String performOperatorAction(String operator, String expression) {
        if (!expression.isEmpty() && isADigit(expression.substring(expression.length()-1))) {
            return expression.concat(SPACE + operator + SPACE);
        }
        return expression;
    }

    @Override
    public String performSettableButtonAction(String expression) {
        return rpnCalculator.evaluateInfixExpression(expression);
    }

    @Override
    public String appendDecimal(String expression) {
        if (expression.isEmpty()) {
            return expression.concat("0.");
        }
        if(!isADigit(expression.substring(expression.length()-1))) return expression;
        if (expression.contains(SPACE) && !expression.substring(expression.lastIndexOf(SPACE), expression.length()-1).contains(DECIMAL_POINT)) {
            return expression.concat(DECIMAL_POINT);
        } else if (!expression.contains(DECIMAL_POINT)){
            return expression.concat(DECIMAL_POINT);
        }
        return expression;
    }

    @Override
    public String deleteCharacter(String expression) {
        if(expression.length() <= 1) {
            return "";
        }
        if (expression.lastIndexOf(DECIMAL_POINT) == expression.length() - 2) {
            return expression.substring(0, expression.length() - 2);
        }
        if(expression.lastIndexOf(SPACE) == expression.length()-1){
            return expression.substring(0, expression.length()-3);
        }
        return expression.substring(0, expression.length()-1);
    }


}

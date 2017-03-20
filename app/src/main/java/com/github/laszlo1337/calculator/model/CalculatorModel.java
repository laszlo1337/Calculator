package com.github.laszlo1337.calculator.model;


import com.github.laszlo1337.calculator.presenter.mode.CalculatorMode;

import static com.github.laszlo1337.calculator.model.Symbols.DECIMAL_POINT;
import static com.github.laszlo1337.calculator.model.Symbols.LINE_BREAK;
import static com.github.laszlo1337.calculator.model.Symbols.MINUS;

public final class CalculatorModel {
    private final String DEFAULT_CALCULATOR_STATE = "0";

    //renamed currentExpression to exp because it was too long//
    private String exp;
    private CalculatorMode calculatorMode;
    private CalculationResultRelay calculationResultRelay;

    public CalculatorModel() {
        exp = "";
    }


    public void performOperatorAction(String operator) {
        if (operator.equals(MINUS) && (exp.isEmpty() || exp.endsWith(LINE_BREAK))) {
            exp += MINUS;
        } else {
            exp = calculatorMode.performOperatorAction(operator, exp);
        }
        calculationResultRelay.onResultObtained(true, exp);
    }

    public void performSettableButtonAction() {
        exp = calculatorMode.performSettableButtonAction(exp);
        calculationResultRelay.onResultObtained(true, exp);
    }


    public boolean isADigit(String number) {
        if (number.matches("\\d")) {
            return true;
        }
        return false;
    }

    public void deleteCharacter() {
        if (exp.length() == 1) {
            exp = exp.substring(0, exp.length() - 1);
            calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
        } else if (exp.length() > 1) {
            /**
             * if decimal is present, delete two characters. 3.1 becomes 3
             */
            if (exp.lastIndexOf(DECIMAL_POINT) == exp.length() - 2) {
                exp = exp.substring(0, exp.length() - 2);
            } else {
                exp = exp.substring(0, exp.length() - 1);
            }
            calculationResultRelay.onResultObtained(true, exp);
        }
    }

    public void resetCalculatorState(){
        this.deleteExpression();
    }

    public void deleteExpression() {
        exp = "";
        calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
    }

    // TODO: 2017-03-15 find a way to append max 9 digits in one line
    public void appendNumber(String number) {
        /**
         * the first digit shouldn't be a zero
         */
        if (exp.length() == 0 && number.equals("0")) {
            return;
        } else {
            exp += number;
            calculationResultRelay.onResultObtained(true, exp);
        }

    }

    public void appendDecimal() {
        if (exp.length() > 0 && isADigit(exp.substring(exp.length() - 1))) {
            exp += ".";
        } else if (exp.isEmpty()) {
            exp += "0.";
        }
        calculationResultRelay.onResultObtained(true, exp);
    }

    public void setCalculatorMode(CalculatorMode calculatorMode) {
        this.calculatorMode = calculatorMode;
    }

    public void setCalculationResultListener(CalculationResultRelay result) {
        this.calculationResultRelay = result;
        calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
    }
}

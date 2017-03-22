package com.github.laszlo1337.calculator.model;


import com.github.laszlo1337.calculator.presenter.mode.CalculatorMode;

import static com.github.laszlo1337.calculator.model.Helpers.DEFAULT_CALCULATOR_STATE;
import static com.github.laszlo1337.calculator.model.Helpers.LINE_BREAK;
import static com.github.laszlo1337.calculator.model.Helpers.MINUS;

public final class CalculatorModel {


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

    public void deleteCharacter() {
        exp = calculatorMode.deleteCharacter(exp);
        if (exp.isEmpty()) {
            calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
        } else {
            calculationResultRelay.onResultObtained(true, exp);
        }
    }

    public void performSettableButtonAction() {
        exp = calculatorMode.performSettableButtonAction(exp);
        calculationResultRelay.onResultObtained(true, exp);
    }

    public void resetCalculatorState() {
        this.deleteExpression();
    }

    public void deleteExpression() {
        exp = "";
        calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
    }

    public void appendNumber(String number) {
        if (exp.length() == 0 && number.equals("0")) {
            return;
        } else {
            exp += number;
            calculationResultRelay.onResultObtained(true, exp);
        }
    }

    public void appendDecimal() {
        exp = calculatorMode.appendDecimal(exp);
        calculationResultRelay.onResultObtained(true, exp);
    }

    public static boolean isADigit(String number) {
        return (number.matches("\\d"));
    }

    public void setCalculatorMode(CalculatorMode calculatorMode) {
        this.calculatorMode = calculatorMode;
    }

    public void setCalculationResultListener(CalculationResultRelay result) {
        this.calculationResultRelay = result;
        calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
    }
}

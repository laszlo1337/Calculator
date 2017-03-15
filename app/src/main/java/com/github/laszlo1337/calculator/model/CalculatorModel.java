package com.github.laszlo1337.calculator.model;


import com.github.laszlo1337.calculator.presenter.mode.CalculatorMode;

public final class CalculatorModel {
    private final String DEFAULT_CALCULATOR_STATE = "0";
    public static final String MINUS = "-";

    //renamed currentExpression to exp because it was too long//
    private String exp = "";
    private CalculatorMode calculatorMode;
    private CalculationResultRelay calculationResultRelay;

    public CalculatorModel() {

    }


    public void performOperatorAction(String operator) {
        if (operator.equals(MINUS) && (exp.isEmpty() || exp.endsWith("\n"))) {
            exp += MINUS;
            calculationResultRelay.onResultObtained(true, exp);
            return;
        }
        exp = calculatorMode.performOperatorAction(operator, exp);
        calculationResultRelay.onResultObtained(true, exp);
    }

    public void performSettableButtonAction() {
        exp = calculatorMode.performSettableButtonAction(exp);
        calculationResultRelay.onResultObtained(true, exp);
    }


    public boolean isANumber(String number) {
        if (number.matches("\\d")) {
            return true;
        }
        return false;
    }

    public void deleteCharacter() {
        if (exp.length() > 1) {
            if (exp.lastIndexOf(".") == exp.length() - 2) {
                exp = exp.substring(0, exp.length() - 2);
                calculationResultRelay.onResultObtained(true, exp);
            } else {
                exp = exp.substring(0, exp.length() - 1);
                calculationResultRelay.onResultObtained(true, exp);
            }
        } else if (exp.length() == 1) {
            exp = exp.substring(0, exp.length() - 1);
            calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
        }
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
        if (exp.length() > 0 && isANumber(exp.substring(exp.length() - 1))) {
            exp += ".";
            calculationResultRelay.onResultObtained(true, exp);
        }
    }

    public void setCalculatorMode(CalculatorMode calculatorMode) {
        this.calculatorMode = calculatorMode;
    }

    public void setCalculationResultListener(CalculationResultRelay result) {
        this.calculationResultRelay = result;
        calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
    }
}

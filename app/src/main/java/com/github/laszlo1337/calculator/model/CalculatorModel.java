package com.github.laszlo1337.calculator.model;

import android.util.Log;

import com.github.laszlo1337.calculator.presenter.mode.CalculatorMode;

public final class CalculatorModel {
    private final String DEFAULT_CALCULATOR_STATE = "0";
    private final String NEXT_LINE = "\n";

    private StringBuilder currentExpression;
    private CalculatorMode calculatorMode;
    private CalculationResultRelay calculationResultRelay;

    public CalculatorModel() {
        this.currentExpression = new StringBuilder();
        this.calculatorMode.setCurrentExpression(currentExpression);
    }



    public void performOperatorAction(String operator) {
        /**
         * check if the last character of currentExpression is a number
         */
        if(isANumber(currentExpression.substring(currentExpression.length()-1))){
            calculatorMode.performOperatorAction();

        }
    }

    public void performSettableButtonAction() {
        calculatorMode.performSettableButtonAction();
    }

    public void validateExpression() {
        calculatorMode.validateExpression();
    }

    public boolean isANumber(String number) {
        if (number.matches("\\d")) {
            return true;
        }
        return false;
    }

    public void deleteCharacter() {
        if (currentExpression.length() > 1) {
            currentExpression.deleteCharAt(currentExpression.length() - 1);
            calculationResultRelay.onResultObtained(true, currentExpression.toString());
        } else if (currentExpression.length() == 1) {
            currentExpression.deleteCharAt(0);
            calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
        }
    }

    public void deleteExpression() {
        currentExpression.delete(0, currentExpression.length());
        calculationResultRelay.onResultObtained(true, DEFAULT_CALCULATOR_STATE);
    }

    public void appendNumber(String number) {
        /**
         * the first number shouldn't be a zero
         */
        if (currentExpression.length() == 0 && number.equals("0")) {
            return;
        } else {
            currentExpression.append(number);
            calculationResultRelay.onResultObtained(true, currentExpression.toString());
        }
    }

    public void appendDecimal() {
        if (currentExpression.length() > 0 && isANumber(currentExpression.substring(currentExpression.length() - 1))) {
            currentExpression.append(".");
            calculationResultRelay.onResultObtained(true, currentExpression.toString());
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

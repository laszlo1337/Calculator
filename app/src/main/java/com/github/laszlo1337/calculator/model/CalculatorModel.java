package com.github.laszlo1337.calculator.model;

import com.github.laszlo1337.calculator.presenter.mode.CalculatorMode;

public final class CalculatorModel {

    private String currentExpression;
    private CalculatorMode calculatorMode;
    private CalculationResultRelay calculationResultRelay;

    public CalculatorModel() {
        this.currentExpression = "0";
    }

    public void deleteCharacter() {
        if (currentExpression.length() > 1) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResultRelay.onResultObtained(true, currentExpression);
        } else if (currentExpression.length() == 1) {
            currentExpression = "0";
            calculationResultRelay.onResultObtained(true, currentExpression);
        }
    }

    public void deleteExpression() {
        currentExpression = "0";
        calculationResultRelay.onResultObtained(true, currentExpression);
    }

    public void appendNumber(String number) {

    }

    public void appendDecimal() {

    }

    public void setCalculatorMode(CalculatorMode calculatorMode) {
        this.calculatorMode = calculatorMode;
    }

    public void setCalculationResultListener(CalculationResultRelay result) {
        this.calculationResultRelay = result;
    }
}

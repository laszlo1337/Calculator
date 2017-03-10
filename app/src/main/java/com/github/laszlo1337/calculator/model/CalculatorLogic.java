package com.github.laszlo1337.calculator.model;

/**
 * Created by laszlo on 2017-03-06.
 */

public class CalculatorLogic {

    private CalculatorMode calculatorMode;
    private String currentExpression;
    private CalculationResultRelay calculationResultRelay;

    public interface CalculationResultRelay {
        void onResultObtained(boolean isValid, String result);
    }

    public void setCalculationResultListener(CalculationResultRelay result) {
        this.calculationResultRelay = result;
    }

    public CalculatorLogic() {
        this.currentExpression = "0";
    }

    public void setCalculatorMode(CalculatorMode calculatorMode) {
        this.calculatorMode = calculatorMode;
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
}

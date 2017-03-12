package com.github.laszlo1337.calculator.presenter.mode;

public final class CalculatorModeBasic implements CalculatorMode {

    private StringBuilder currentExpression;

    /**
     *
     */
    @Override
    public void performOperatorAction() {

    }

    @Override
    public void performSettableButtonAction() {

    }

    @Override
    public void validateExpression() {

    }


    @Override
    public void setCurrentExpression(StringBuilder expression) {
        this.currentExpression = expression;
    }
}

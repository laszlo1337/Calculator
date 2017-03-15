package com.github.laszlo1337.calculator.presenter.mode;

import com.github.laszlo1337.calculator.model.RpnCalculator;

public final class CalculatorModeBasic implements CalculatorMode {

    private final RpnCalculator rpnCalculator;

    public CalculatorModeBasic(RpnCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    @Override
    public String performOperatorAction(String operator, String expression) {
        return null;
    }

    @Override
    public String performSettableButtonAction(String expression) {
        return null;
    }


}

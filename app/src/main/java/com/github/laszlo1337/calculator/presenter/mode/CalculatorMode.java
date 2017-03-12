package com.github.laszlo1337.calculator.presenter.mode;

/**
 * Created by laszlo on 2017-03-10.
 */

public interface CalculatorMode {
    void setCurrentExpression(StringBuilder expression);
    void performOperatorAction();
    void performSettableButtonAction();
    void validateExpression();
}


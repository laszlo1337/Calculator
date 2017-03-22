package com.github.laszlo1337.calculator.presenter.mode;

/**
 * Created by laszlo on 2017-03-10.
 */

public interface CalculatorMode {
    String performOperatorAction(String operator, String expression);
    String performSettableButtonAction(String expression);
    String appendDecimal(String expression);
    String deleteCharacter(String expression);
}


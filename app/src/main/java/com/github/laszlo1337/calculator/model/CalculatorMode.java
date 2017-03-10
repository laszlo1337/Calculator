package com.github.laszlo1337.calculator.model;

/**
 * Created by laszlo on 2017-03-10.
 */

public interface CalculatorMode {
    void performOperatorAction();
    void performSettableButtonAction();
    void validateExpression();
}

 class ModeReversedPolishNotation implements CalculatorMode {


    @Override
    public void performOperatorAction() {

    }

    @Override
    public void performSettableButtonAction() {

    }

    @Override
    public void validateExpression() {

    }
}

class ModeBasic implements CalculatorMode {

    @Override
    public void performOperatorAction() {

    }

    @Override
    public void performSettableButtonAction() {

    }

    @Override
    public void validateExpression() {

    }
}

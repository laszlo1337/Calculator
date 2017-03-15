package com.github.laszlo1337.calculator.presenter;

import com.github.laszlo1337.calculator.model.CalculationResultRelay;
import com.github.laszlo1337.calculator.model.CalculatorModel;
import com.github.laszlo1337.calculator.model.RpnCalculator;
import com.github.laszlo1337.calculator.presenter.mode.CalculatorModeBasic;
import com.github.laszlo1337.calculator.presenter.mode.CalculatorModeReversedPolishNotation;
import com.github.laszlo1337.calculator.presenter.mode.Mode;
import com.github.laszlo1337.calculator.view.CalculatorDisplayView;
import com.github.laszlo1337.calculator.view.CalculatorKeyPadView;

/**
 * Created by laszlo on 2017-03-06.
 */

public final class Calculator implements CalculatorPresenter, CalculatorPresenter.ModeSelectorRelay {

    private CalculatorDisplayView calculatorDisplayView;
    private CalculatorKeyPadView calculatorKeyPadView;
    private CalculatorModel calculatorModel;
    private RpnCalculator rpnCalculator;

    private CalculatorModeBasic calculatorModeBasic;
    private CalculatorModeReversedPolishNotation calculatorModeReversedPolishNotation;

    private final CalculationResultRelay calculationResultRelay = new CalculationResultRelay() {
        @Override
        public void onResultObtained(boolean isValid, String result) {
            if (isValid) {
                calculatorDisplayView.showCalculationResult(result);
            }
        }
    };

    public Calculator(CalculatorDisplayView calculatorDisplayView, CalculatorKeyPadView calculatorKeyPadView) {
        this.calculatorDisplayView = calculatorDisplayView;
        this.calculatorKeyPadView = calculatorKeyPadView;
        this.calculatorModel = new CalculatorModel();
        this.calculatorModel.setCalculationResultListener(calculationResultRelay);
        this.rpnCalculator = new RpnCalculator();
        this.calculatorModeBasic = new CalculatorModeBasic(rpnCalculator);
        this.calculatorModeReversedPolishNotation = new CalculatorModeReversedPolishNotation(rpnCalculator);
    }

    @Override
    public void onNumberClick(String number) {
        calculatorModel.appendNumber(number);
    }

    @Override
    public void onDecimalClick() {
        calculatorModel.appendDecimal();
    }

    @Override
    public void onSettableButtonClick() {
        calculatorModel.performSettableButtonAction();
    }

    @Override
    public void onOperatorClick(String operator) {
        calculatorModel.performOperatorAction(operator);
    }

    @Override
    public void onDeleteClick() {
        calculatorModel.deleteCharacter();
    }

    @Override
    public void onClearExpression() {
        calculatorModel.deleteExpression();
    }


    @Override
    public void selectBasicMode() {
        calculatorKeyPadView.switchButtonRole(Mode.MODE_BASIC);
        calculatorDisplayView.setRpnIndicator(false);
        calculatorModel.setCalculatorMode(calculatorModeBasic);
    }

    @Override
    public void selectRpnMode() {
        calculatorKeyPadView.switchButtonRole(Mode.MODE_RPN);
        calculatorDisplayView.setRpnIndicator(true);
        calculatorModel.setCalculatorMode(calculatorModeReversedPolishNotation);
    }

}

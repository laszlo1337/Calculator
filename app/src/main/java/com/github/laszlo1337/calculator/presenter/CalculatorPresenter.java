package com.github.laszlo1337.calculator.presenter;

import com.github.laszlo1337.calculator.model.CalculatorLogic;
import com.github.laszlo1337.calculator.view.Display;

/**
 * Created by laszlo on 2017-03-06.
 */

public class CalculatorPresenter implements Presenter, Presenter.ModeSelectorRelay, CalculatorLogic.CalculationResultRelay {

    private static final int MODE_BASIC = 1;
    private static final int MODE_RPN = 0;

    private Display display;
    private CalculatorLogic calculatorLogic;
    private Presenter.SettableButtonSwitch buttonSwitch;

    public CalculatorPresenter(Display display, Presenter.SettableButtonSwitch buttonSwitch){
        this.buttonSwitch = buttonSwitch;
        this.display = display;
        this.calculatorLogic = new CalculatorLogic();
        calculatorLogic.setCalculationResultListener(this);
    }

    @Override
    public void onNumberClick(String number) {
        calculatorLogic.appendNumber(number);
    }

    @Override
    public void onDecimalClick() {

    }

    @Override
    public void onSettableButtonClick() {

    }

    @Override
    public void onOperatorClick(String operator) {

    }

    @Override
    public void onDeleteClick() {
        calculatorLogic.deleteCharacter();
    }

    @Override
    public void onClearExpression() {
        calculatorLogic.deleteExpression();
    }


    @Override
    public void onResultObtained(boolean isValid, String result) {
        if(isValid){
            display.showCalculationResult(result);
        }
    }

    @Override
    public void selectBasicMode() {
       // calculatorLogic.setCalculatorMode();
        buttonSwitch.switchButtonRole(MODE_BASIC);
        display.setRpnIndicator(false);
    }

    @Override
    public void selectRpnMode() {
       // calculatorLogic.setCalculatorMode();
        buttonSwitch.switchButtonRole(MODE_RPN);
        display.setRpnIndicator(true);
    }

}

package com.github.laszlo1337.calculator.presenter;

import com.github.laszlo1337.calculator.model.CalculatorLogic;
import com.github.laszlo1337.calculator.view.Display;

/**
 * Created by laszlo on 2017-03-06.
 */

public class CalculatorPresenter implements Presenter, CalculatorLogic.CalculationResultRelay {

    private Display display;
    private CalculatorLogic calculatorLogic;

    public CalculatorPresenter(Display display){
        this.display = display;
        this.calculatorLogic = new CalculatorLogic();
        calculatorLogic.setCalculationResultListener(this);
    }

    public void setDisplay(Display display){
        this.display = display;
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
}

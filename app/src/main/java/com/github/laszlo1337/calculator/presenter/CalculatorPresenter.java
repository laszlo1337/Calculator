package com.github.laszlo1337.calculator.presenter;

import com.github.laszlo1337.calculator.view.Display;

/**
 * Created by laszlo on 2017-03-06.
 */

public class CalculatorPresenter implements Presenter {

    private Display display;

    public void setDisplay(Display display){
        this.display = display;
    }

    @Override
    public void onNumberClick(String number) {

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

    }

    @Override
    public void onClearExpression() {

    }
}

package com.github.laszlo1337.calculator.view;

import android.widget.Button;

public interface CalculatorKeyPadView {

    void switchButtonRole(int mode);

    void onNumberClick(Button button);

    void onOperatorClick(Button button);

    void onDecimalClick(Button button);

    void onSettableButtonClick(Button button);

    void onDeleteClick(Button button);

    void onClearExpression(Button button);

}

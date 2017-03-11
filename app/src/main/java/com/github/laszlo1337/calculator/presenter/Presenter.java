package com.github.laszlo1337.calculator.presenter;

/**
 * Created by laszlo on 2017-03-06.
 */

 public interface Presenter {
    void onNumberClick(String number);

    void onDecimalClick();

    void onSettableButtonClick();

    void onOperatorClick(String operator);

    void onDeleteClick();

    void onClearExpression();

   interface ModeSelectorRelay{
       void selectBasicMode();
       void selectRpnMode();
   }

   interface SettableButtonSwitch{
       void switchButtonRole(int mode);
   }
}
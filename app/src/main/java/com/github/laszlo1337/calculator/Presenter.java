package com.github.laszlo1337.calculator;

/**
 * Created by laszlo on 2017-03-06.
 */

 public interface Presenter {
    void onNumberClick(String number);

    void onDecimalClick();

    void onEvaluate();

    void onOperatorClick(String operator);

    void onDeleteClick();

    void onClearExpression();
}
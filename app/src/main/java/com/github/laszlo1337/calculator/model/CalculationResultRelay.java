package com.github.laszlo1337.calculator.model;

/**
 * Created by laszlo on 2017-03-06.
 */
public interface CalculationResultRelay {
    void onResultObtained(boolean isValid, String result);
}

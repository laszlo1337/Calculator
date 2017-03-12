package com.github.laszlo1337.calculator.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.laszlo1337.calculator.R;
import com.github.laszlo1337.calculator.presenter.CalculatorPresenter;
import com.github.laszlo1337.calculator.presenter.mode.Mode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public final class CalculatorKeyPadFragment extends Fragment implements CalculatorKeyPadView {

    @BindView(R.id.btn_settable_button)
    Button settableButton;

    private final int MODE_BASIC_TEXT_SIZE = 34;
    private final int MODE_RPN_TEXT_SIZE = 14;

    private CalculatorPresenter calculatorPresenter;

    public CalculatorKeyPadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void switchButtonRole(int mode) {
        switch (mode) {
            case Mode.MODE_BASIC:
                settableButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, MODE_BASIC_TEXT_SIZE);
                settableButton.setText(R.string.mode_basic);
                break;
            case Mode.MODE_RPN:
                settableButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, MODE_RPN_TEXT_SIZE);
                settableButton.setText(R.string.mode_rpn);
                break;
        }
    }

    @OnClick({R.id.btn_number_one, R.id.btn_number_two, R.id.btn_number_three, R.id.btn_number_four, R.id.btn_number_five,
            R.id.btn_number_six, R.id.btn_number_seven, R.id.btn_number_eight, R.id.btn_number_nine, R.id.btn_number_zero})
    @Override
    public void onNumberClick(Button button) {
        calculatorPresenter.onNumberClick((String) button.getText());
    }

    @OnClick({R.id.btn_add, R.id.btn_subtract, R.id.btn_divide, R.id.btn_multiply})
    @Override
    public void onOperatorClick(Button button) {
        calculatorPresenter.onOperatorClick((String) button.getText());
    }

    @OnClick(R.id.btn_period)
    @Override
    public void onDecimalClick(Button button) {
        calculatorPresenter.onDecimalClick();
    }

    @OnClick(R.id.btn_settable_button)
    @Override
    public void onSettableButtonClick(Button button) {
        calculatorPresenter.onSettableButtonClick();
    }

    @OnClick(R.id.btn_delete)
    @Override
    public void onDeleteClick(Button button) {
        calculatorPresenter.onDeleteClick();
    }

    @OnClick(R.id.btn_clear)
    @Override
    public void onClearExpression(Button button) {
        calculatorPresenter.onClearExpression();
    }

    public void setCalculatorPresenter(CalculatorPresenter calculatorPresenter) {
        this.calculatorPresenter = calculatorPresenter;
    }


}

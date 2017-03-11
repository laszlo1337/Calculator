package com.github.laszlo1337.calculator.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.laszlo1337.calculator.presenter.Presenter;
import com.github.laszlo1337.calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class KeyPadFragment extends Fragment implements Presenter.SettableButtonSwitch {

    @BindView(R.id.btn_settable_button)
    Button settableButton;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private Presenter presenter;

    @OnClick({R.id.btn_number_one, R.id.btn_number_two, R.id.btn_number_three, R.id.btn_number_four,
            R.id.btn_number_six, R.id.btn_number_seven, R.id.btn_number_eight, R.id.btn_number_nine, R.id.btn_number_zero})
    public void onNumberClick(Button b) {
        presenter.onNumberClick((String) b.getText());
    }

    @OnClick({R.id.btn_add, R.id.btn_subtract, R.id.btn_divide, R.id.btn_multiply})
    public void onOperatorClick(Button b) {
        presenter.onOperatorClick((String) b.getText());
    }

    @OnClick(R.id.btn_period)
    public void onDecimalClick(Button b) {
        presenter.onDecimalClick();
    }

    @OnClick(R.id.btn_settable_button)
    public void onSettableButtonClick(Button b) {
        presenter.onSettableButtonClick();
    }

    @OnClick(R.id.btn_delete)
    public void onDeleteClick(Button b) {
        presenter.onDeleteClick();
    }

    @OnClick(R.id.btn_clear)
    public void onClearExpression(Button b) {
        presenter.onClearExpression();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public KeyPadFragment() {
        // Required empty public constructor
    }

    //public static KeyPadFragment newInstance(){return new KeyPadFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void switchButtonRole(int mode) {
        switch (mode) {
            case 1:
                settableButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);
                settableButton.setText(R.string.mode_basic);
                break;
            case 0:
                settableButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                settableButton.setText(R.string.mode_rpn);
                break;
        }
    }
}

package com.github.laszlo1337.calculator.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.laszlo1337.calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CalculatorDisplayFragment extends Fragment implements CalculatorDisplayView {

    @BindView(R.id.display_textview)
    TextView displayTextView;
    @BindView(R.id.display_scrollview)
    ScrollView scrollView;
    @BindView(R.id.display_rpn_mode_indicator)
    TextView rpnIndicator;

    public CalculatorDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showCalculationResult(String result) {
        displayTextView.setText(result);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    public void setRpnIndicator(boolean isOn) {
        if (isOn) {
            rpnIndicator.setText("RPN");
        } else {
            rpnIndicator.setText("");
        }
    }
}

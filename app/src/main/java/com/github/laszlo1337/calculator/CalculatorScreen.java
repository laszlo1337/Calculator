package com.github.laszlo1337.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorScreen extends Fragment implements Display {
    @BindView(R.id.display_text)
    private TextView display;


    //public static CalculatorScreen newInstance(){return new CalculatorScreen();}

    public CalculatorScreen() {
        //required empty constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this,view);

        return view;
    }


    @Override
    public void showCalculationResult(String result) {
        display.setText(result);
    }
}

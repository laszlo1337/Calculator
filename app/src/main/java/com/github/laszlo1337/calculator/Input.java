package com.github.laszlo1337.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class Input extends Fragment {

    private Presenter presenter;

    @OnClick({R.id.btn_number_one,R.id.btn_number_two,R.id.btn_number_three,R.id.btn_number_four,
            R.id.btn_number_six,R.id.btn_number_seven,R.id.btn_number_eight,R.id.btn_number_nine,R.id.btn_number_zero})
    public void onNumberClick(Button b){
        presenter.onNumberClick((String)b.getText());
    }

    @OnClick({R.id.btn_add,R.id.btn_subtract,R.id.btn_divide,R.id.btn_multiply})
    public void onOperatorClick(Button b){
        presenter.onOperatorClick((String)b.getText());
    }

    @OnClick(R.id.btn_period)
    public void onDecimalClick(Button b){
        presenter.onDecimalClick();
    }

    @OnClick(R.id.btn_evaluate)
    public void onEvaluate(Button b){
        presenter.onEvaluate();
    }

    @OnClick(R.id.btn_delete)
    public void onDeleteClick(Button b){
        presenter.onDeleteClick();
    }

    @OnClick(R.id.btn_clear)
    public void onClearExpression(Button b){
        presenter.onClearExpression();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public Input() {
        // Required empty public constructor
    }

    //public static Input newInstance(){return new Input();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

}

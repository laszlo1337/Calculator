package com.github.laszlo1337.calculator.model;

import java.util.Stack;

/**
 * Created by laszlo on 2017-03-12.
 */

public class RpnCalculator {

    Stack<String> operators;
    Stack<String> operands;

    public RpnCalculator(){
        this.operators = new Stack<>();
        this.operands = new Stack<>();
    }


    public void push(String operand){
        operands.push(operand);
    }

    public String evaluate(){
        return null;
    }

}

package io.lhft.test.calculator.service;

import org.springframework.stereotype.Component;

import io.lhft.test.calculator.exception.InvalidOperandException;

@Component
public class Division implements Operator{

    @Override
    public Double apply(Double lOperand, Double rOperand) {
        if(rOperand == 0)
            throw new InvalidOperandException("InvalidOperand");
        return lOperand/rOperand;
    }

}

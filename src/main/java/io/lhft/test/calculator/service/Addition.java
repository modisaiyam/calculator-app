package io.lhft.test.calculator.service;

import org.springframework.stereotype.Component;

@Component
public class Addition implements Operator{
    
    @Override
    public Double apply(Double lOperand, Double rOperand) {
        return lOperand+rOperand;
    }

}

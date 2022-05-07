package io.lhft.test.calculator.service;

import org.springframework.stereotype.Component;

@Component
public class Multiplication implements Operator{

    @Override
    public Double apply(Double lOperand, Double rOperand) {
        return lOperand*rOperand;
    }

}

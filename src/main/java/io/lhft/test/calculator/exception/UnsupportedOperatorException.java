package io.lhft.test.calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class UnsupportedOperatorException extends RuntimeException{
    public UnsupportedOperatorException(String mes){
        super(mes);
    }
}
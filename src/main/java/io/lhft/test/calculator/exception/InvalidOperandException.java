package io.lhft.test.calculator.exception;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class InvalidOperandException extends RuntimeException{
    
    public InvalidOperandException(String mes){
        super(mes);
    }
}

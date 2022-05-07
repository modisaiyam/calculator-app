package io.lhft.test.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.lhft.test.SwaggerCodgen.model.CalculationError;
import io.lhft.test.calculator.exception.InvalidOperandException;

@RestControllerAdvice
public class CalculatorControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidOperandException.class, UnsupportedOperationException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CalculationError resourceNotFoundException(Exception ex) {
        CalculationError message = new CalculationError().errorMessage(ex.getMessage());    
        return message;
    }
}
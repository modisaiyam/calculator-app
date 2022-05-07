package io.lhft.test.calculator.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.lhft.test.calculator.exception.UnsupportedOperatorException;

@Component
public class OperatorFactory {

    private static Logger logger = LoggerFactory.getLogger(OperatorFactory.class);

    @Autowired
    Addition addition;

    @Autowired
    Subtraction subtraction;

    @Autowired
    Multiplication multiplication;

    @Autowired
    Division division;
    
    Set<String> validOperatorSet;
    
    public OperatorFactory(){
        this.validOperatorSet = new HashSet<>();
        validOperatorSet.add("+");
        validOperatorSet.add("-");
        validOperatorSet.add("*");
        validOperatorSet.add("/");
    }
    
    public Operator getOperator(String operator){
        if(!validOperatorSet.contains(operator)){
            logger.error("Unsupported Operator!");
            throw new UnsupportedOperatorException("Unsupported Operator!");
        }
        Operator result = null;
        switch(operator){
            case "+": result = addition; break;
            case "-": result = subtraction; break;
            case "*": result = multiplication; break;
            case "/": result = division; break;
        }
        logger.debug("Input {} Output {}", operator, result);
        return result;
    }

    public Set<String> getValidOperatorSet(){
        return validOperatorSet;
    }
}

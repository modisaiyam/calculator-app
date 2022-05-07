package io.lhft.test.calculator.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.lhft.test.SwaggerCodgen.model.CalculationReq;
import io.lhft.test.SwaggerCodgen.model.CalculationRes;

@Service
public class CalculatorService {

    @Autowired
    OperatorFactory operatorFactory;

    public CalculationRes doCalculation(CalculationReq calculationReq) {
        CalculationRes calculationRes = new CalculationRes();
        calculationRes.setlOperand(calculationReq.getlOperand());
        calculationRes.setOperation(calculationReq.getOperation());
        calculationRes.setrOperand(calculationReq.getrOperand());
        double result  = operatorFactory
                            .getOperator(calculationReq.getOperation())
                            .apply(calculationReq.getlOperand(), calculationReq.getrOperand());
        calculationRes.setResult(result);
        return calculationRes;
    }

    public List<String> getSupportedOperations() {
        return operatorFactory.getValidOperatorSet().stream().collect(Collectors.toList());
    }

}

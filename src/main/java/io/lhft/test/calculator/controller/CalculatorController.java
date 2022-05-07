package io.lhft.test.calculator.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import io.lhft.test.SwaggerCodgen.api.CalculationApi;
import io.lhft.test.SwaggerCodgen.model.CalculationReq;
import io.lhft.test.SwaggerCodgen.model.CalculationRes;
import io.lhft.test.calculator.service.CalculatorService;

@RestController
public class CalculatorController implements CalculationApi {

    @Autowired
    CalculatorService calculatorService;

    @Override
    public ResponseEntity<List<CalculationRes>> doCalculation(List<CalculationReq> calculationReq) {
        List<CalculationRes> calculationRes = calculationReq.parallelStream().map(e->calculatorService.doCalculation(e)).collect(Collectors.toList());
        return new ResponseEntity<>(calculationRes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> calculationGet() {
        return new ResponseEntity<>(calculatorService.getSupportedOperations(), HttpStatus.OK);
    }

}

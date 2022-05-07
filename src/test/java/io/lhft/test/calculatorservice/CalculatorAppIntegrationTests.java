package io.lhft.test.calculatorservice;


import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import io.lhft.test.SwaggerCodgen.model.CalculationReq;
import io.lhft.test.calculator.CalculatorApp;


@SpringBootTest(classes = CalculatorApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class CalculatorAppIntegrationTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetCalculation(){
		Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/calculation",String.class)).isEqualTo("[\"*\",\"+\",\"-\",\"/\"]");
	}

	@Test
	public void testPostCalculation() throws Exception {
		List<CalculationReq> calculationReqList = new ArrayList<>();
		CalculationReq calculationReq = new CalculationReq();
		calculationReq.setlOperand(1d);
		calculationReq.setOperation("+");
		calculationReq.setrOperand(1d);
		calculationReqList.add(calculationReq);
		calculationReq = new CalculationReq();
		calculationReq.setlOperand(2d);
		calculationReq.setOperation("-");
		calculationReq.setrOperand(1d);
		calculationReqList.add(calculationReq);
		calculationReq = new CalculationReq();
		calculationReq.setlOperand(5d);
		calculationReq.setOperation("*");
		calculationReq.setrOperand(2d);
		calculationReqList.add(calculationReq);
		calculationReq = new CalculationReq();
		calculationReq.setlOperand(10d);
		calculationReq.setOperation("/");
		calculationReq.setrOperand(2d);
		calculationReqList.add(calculationReq);
		Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/calculation",calculationReqList, String.class)).contains("\"result\":2");
	}	


	@Test
	public void testPostCalculationErrorUnsupportedOperator() throws Exception {
		List<CalculationReq> calculationReqList = new ArrayList<>();
		CalculationReq calculationReq = new CalculationReq();
		calculationReq.setlOperand(1d);
		calculationReq.setOperation("&");
		calculationReq.setrOperand(1d);
		calculationReqList.add(calculationReq);
		Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/calculation",calculationReqList, String.class)).contains("Unsupported Operator!");
	}

	@Test
	public void testPostCalculationErrorInvalidOperand() throws Exception {
		List<CalculationReq> calculationReqList = new ArrayList<>();
		CalculationReq calculationReq = new CalculationReq();
		calculationReq.setlOperand(1d);
		calculationReq.setOperation("/");
		calculationReq.setrOperand(0d);
		calculationReqList.add(calculationReq);
		Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/calculation",calculationReqList, String.class)).contains("InvalidOperand");
	}

}

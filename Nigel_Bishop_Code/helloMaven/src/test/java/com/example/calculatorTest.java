package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.calculator.Calculator;

public class calculatorTest {
	
	@Test
	public void AddTest() {
		assertEquals(5, Calculator.add(2, 3));
	}
	
//	@Test
//	public void MultipleTest() {
//		fail("Method has not been implemented");
//	}
}

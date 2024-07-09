package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {

	@Test
	void calculateSum() {
		MyMath myMath = new MyMath();
		int sum = myMath.calculateSum(new int[] { 1, 2, 3 });

		assertEquals(9, sum);
	}
}
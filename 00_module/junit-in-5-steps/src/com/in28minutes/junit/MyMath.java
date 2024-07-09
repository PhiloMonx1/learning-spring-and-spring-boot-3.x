package com.in28minutes.junit;

import java.util.Arrays;

public class MyMath {

	public int calculateSum(int[] numbers) {
		return Arrays.stream(numbers).sum();
	}
}

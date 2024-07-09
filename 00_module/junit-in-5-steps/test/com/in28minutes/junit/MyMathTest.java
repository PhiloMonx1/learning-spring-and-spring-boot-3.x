package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyMathTest {

	private MyMath myMath;

	@BeforeEach
	void setUp() {
		myMath = new MyMath();
	}

	@Test
	@DisplayName("배열 내의 3개의 숫자를 합산하여 리턴합니다.")
	void calculateSum_ThreeMembers() {
		assertEquals(6, myMath.calculateSum(new int[]{1, 2, 3}));
	}

	@Test
	@DisplayName("빈 배열이라면 0을 리턴합니다.")
	void calculateSum_ZeroMember() {
		assertEquals(0, myMath.calculateSum(new int[]{}));
	}
}
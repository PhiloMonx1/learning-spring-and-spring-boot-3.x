package com.in28minutes.learn_spring_aop.business;

import com.in28minutes.learn_spring_aop.data.DataService;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class BusinessService1 {
	private final DataService dataService;

	public BusinessService1(DataService dataService) {
		this.dataService = dataService;
	}

	public int calculateMax() {
		int[] data = dataService.retrieveData();
		return Arrays.stream(data).max().orElse(0);
	}
}

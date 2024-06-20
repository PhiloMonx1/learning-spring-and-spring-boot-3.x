package com.in28minutes.learn_spring_framework.examples.c1;

import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class BusinessCalculationService {

	private DataService dataService;

	public BusinessCalculationService(DataService dataService) {
		this.dataService = dataService;
	}

	public int findMax() {
		return Arrays.stream(dataService.retrieveData())
				.max().orElse(0);
	}
}

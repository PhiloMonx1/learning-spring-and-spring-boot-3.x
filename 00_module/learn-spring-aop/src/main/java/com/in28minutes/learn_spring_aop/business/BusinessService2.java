package com.in28minutes.learn_spring_aop.business;

import com.in28minutes.learn_spring_aop.data.DataService;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class BusinessService2 {

	private final DataService dataService;

	public BusinessService2(DataService dataService) {
		this.dataService = dataService;
	}

	public int calculateMin() {
		int[] data = dataService.retrieveData();
		if (data.length == 0) {
			throw new IllegalArgumentException("데이터가 비어 있습니다.");
		}
		return Arrays.stream(data).min().getAsInt();
	}
}

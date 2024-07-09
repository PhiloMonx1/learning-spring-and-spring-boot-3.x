package com.in28minutes.mockito.mockito_demo.business;

import java.util.Arrays;

public class SomeBusinessImpl {

	private DataService dataService;

	public SomeBusinessImpl(DataService dataService) {
		this.dataService = dataService;
	}

	public int findTheGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();
		return Arrays.stream(data).max().getAsInt();
	}

}

interface DataService {

	int[] retrieveAllData();
}
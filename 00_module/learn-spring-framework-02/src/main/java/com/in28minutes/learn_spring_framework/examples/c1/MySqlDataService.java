package com.in28minutes.learn_spring_framework.examples.c1;

import org.springframework.stereotype.Repository;

@Repository
public class MySqlDataService implements DataService {

	@Override
	public int[] retrieveData() {
		return new int[]{1, 2, 3, 4, 5};
	}
}

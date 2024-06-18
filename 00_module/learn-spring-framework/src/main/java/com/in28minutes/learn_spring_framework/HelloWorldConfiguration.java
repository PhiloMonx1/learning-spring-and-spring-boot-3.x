package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person(String name, int age) { };
record Address(String firstLine, String city) { };

@Configuration
public class HelloWorldConfiguration {

	@Bean
	public String name() {
		return "EH13";
	}

	@Bean
	public int age() {
		return 30;
	}

	@Bean
	public Person person() {
		return new Person("Van", 33);
	}

	@Bean
	public Address address() {
		return new Address("강남구", "서울특별시");
	}
}

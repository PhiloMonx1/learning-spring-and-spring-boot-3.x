package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person(String name, int age, Address address) { };
record Address(String firstLine, String cit) { };

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
		return new Person("Van", 33, new Address("서초구", "서울특별시"));
	}

	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address());
	}

	@Bean
	public Person person3Parameters(String name, int age, Address address3) {
		return new Person(name, age, address3);
	}

	@Bean(name = "address2")
	public Address address() {
		return new Address("강남구", "서울특별시");
	}

	@Bean(name = "address3")
	public Address address3() {
		return new Address("동작구", "서울특별시");
	}
}

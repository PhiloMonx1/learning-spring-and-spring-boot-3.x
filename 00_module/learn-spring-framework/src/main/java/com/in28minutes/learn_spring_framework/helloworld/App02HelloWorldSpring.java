package com.in28minutes.learn_spring_framework.helloworld;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
		//1: 스프링 컨텍스트 실행 -
		var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

		//2: 스프링 프레이워크가 관리하도록 설정
		//HelloWorldConfiguration - @Configuration
		//name - @Bean

		//3 : 스프링이 관리하고 있는 "name"이라는 이름의 Bean 검색
		System.out.println(context.getBean("name"));
		System.out.println(context.getBean("age"));
		System.out.println(context.getBean("person"));
		System.out.println(context.getBean("person2MethodCall"));
		System.out.println(context.getBean("person3Parameters"));
		System.out.println(context.getBean("address2"));
		//System.out.println(context.getBean(Address.class));
		System.out.println(context.getBeansOfType(Address.class));

		//스프링이 관리하고 있는 모든 Bean 이름 찾기
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);

		//동일한 유형의 Bean
		System.out.println(context.getBean(Person.class));

	}
}

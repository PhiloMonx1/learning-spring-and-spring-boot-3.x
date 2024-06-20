package com.in28minutes.learn_spring_framework.examples.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class SomeClass {

	private SomeDependency someDependency;

	public SomeClass(SomeDependency someDependency) {
		this.someDependency = someDependency;
		System.out.println("모든 의존성이 준비되었습니다.");
	}

	@PostConstruct
	public void initialize() {
		someDependency.getReady();
	}

	@PreDestroy
	public void cleanup() {
		System.out.println("정리");
	}
}

@Component
class SomeDependency {

	public void getReady() {
		System.out.println("SomeDependency : 로직 실행");
	}
}

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {

	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(
				PrePostAnnotationsContextLauncherApplication.class)) {

			Arrays.stream(context.getBeanDefinitionNames())
					.forEach(System.out::println);
		}
	}
}

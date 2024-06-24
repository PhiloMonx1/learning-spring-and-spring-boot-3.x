package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.spring_data_jpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseSpringDataJpaRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1L, "Learn AWS Jpa!", "in28minutes"));
		repository.save(new Course(2L, "Learn Azure Jpa!", "in28minutes"));
		repository.save(new Course(3L, "Learn DevOps Jpa!", "in28minutes"));

		repository.deleteById(1L);

		System.out.println(repository.findById(2L));
		System.out.println(repository.findById(3L));

		System.out.println("=======================================");
		System.out.println(repository.findAll());

		System.out.println("=================findByAuthor : in28minutes=================");
		System.out.println(repository.findByAuthor("in28minutes"));
		System.out.println("=================findByAuthor : empty==================");
		System.out.println(repository.findByAuthor(""));

		System.out.println("=================findByName==================");
		System.out.println(repository.findByName("Learn Azure Jpa!"));
	}
}

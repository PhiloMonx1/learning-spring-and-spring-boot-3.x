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
	}
}

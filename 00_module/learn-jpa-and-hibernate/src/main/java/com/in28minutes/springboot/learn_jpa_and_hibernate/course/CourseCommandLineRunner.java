package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa.CourseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJpaRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.insert(new Course(1L, "Learn AWS Jpa!", "in28minutes"));
		repository.insert(new Course(2L, "Learn Azure Jpa!", "in28minutes"));
		repository.insert(new Course(3L, "Learn DevOps Jpa!", "in28minutes"));

		repository.deleteById(1L);

		System.out.println(repository.findById(2L));
		System.out.println(repository.findById(3L));
	}
}

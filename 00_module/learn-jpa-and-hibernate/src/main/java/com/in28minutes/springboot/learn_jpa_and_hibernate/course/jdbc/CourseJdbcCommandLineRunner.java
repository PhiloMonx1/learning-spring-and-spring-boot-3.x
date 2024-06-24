package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJdbcRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.insert(new Course(1L, "Learn AWS Now!", "in28minutes"));
		repository.insert(new Course(2L, "Learn Azure Now!", "in28minutes"));
		repository.insert(new Course(3L, "Learn DevOps Now!", "in28minutes"));

		repository.deleteById(1L);
	}
}

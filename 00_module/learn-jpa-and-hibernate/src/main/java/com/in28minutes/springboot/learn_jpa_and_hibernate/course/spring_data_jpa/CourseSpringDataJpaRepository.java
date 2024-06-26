package com.in28minutes.springboot.learn_jpa_and_hibernate.course.spring_data_jpa;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

	List<Course> findByAuthor(String author);
	List<Course> findByName(String name);
}

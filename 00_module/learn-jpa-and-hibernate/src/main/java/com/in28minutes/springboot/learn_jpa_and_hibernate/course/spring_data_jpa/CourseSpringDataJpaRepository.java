package com.in28minutes.springboot.learn_jpa_and_hibernate.course.spring_data_jpa;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

}

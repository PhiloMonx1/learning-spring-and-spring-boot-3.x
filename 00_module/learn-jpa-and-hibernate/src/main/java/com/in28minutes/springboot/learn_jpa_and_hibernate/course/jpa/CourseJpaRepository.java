package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	// 삽입
	public void insert(Course course) {
		entityManager.merge(course);
	}

	//조회
	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}

	//삭제
	public void deleteById(long id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}
}

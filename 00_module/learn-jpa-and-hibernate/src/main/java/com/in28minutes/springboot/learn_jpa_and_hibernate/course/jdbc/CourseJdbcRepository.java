package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

	private static String INSERT_COURSE_SQL =
					"""
                        insert into course (id, name, author)
                        values (1, 'Learn AWS', 'in28minutes');
					""";

	@Autowired
	private JdbcTemplate springJdbcTemplate;

	public void insert() {
		springJdbcTemplate.update(INSERT_COURSE_SQL);
	}
}

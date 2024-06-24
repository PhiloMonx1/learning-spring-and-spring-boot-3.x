package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

	private static String INSERT_COURSE_SQL =
			"""
					                   insert into course (id, name, author)
					                   values (?, ?, ?);
					""";

	private static String DELETE_COURSE_SQL =
			"""
					   delete from course 
					   where id=?;
					""";

	private static String SELECT_COURSE_SQL =
			"""
					   select * from course 
					   where id=?;
					""";

	@Autowired
	private JdbcTemplate springJdbcTemplate;

	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_COURSE_SQL, course.getId(), course.getName(),
				course.getAuthor());
	}

	public void deleteById(long id) {
		springJdbcTemplate.update(DELETE_COURSE_SQL, id);
	}

	public Course findById(long id) {
		return springJdbcTemplate.queryForObject(SELECT_COURSE_SQL,
				new BeanPropertyRowMapper<>(Course.class), id);
	}
}

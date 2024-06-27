package com.in28minutes.springboot.myfirstwebapp.todo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUsername(String username);
}

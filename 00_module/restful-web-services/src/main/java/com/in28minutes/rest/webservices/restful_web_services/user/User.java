package com.in28minutes.rest.webservices.restful_web_services.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity(name = "user_details")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 2, message = "'name' 필드는 2글자 이상이어야 합니다.")
	@JsonProperty("user_name")
	private String name;

	@Past(message = "'birthDate' 필드는 미래 날짜일 수 없습니다.")
	@JsonProperty("birth_date")
	private LocalDate birthDate;

	public User() {}

	public User(Integer id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + '}';
	}
}

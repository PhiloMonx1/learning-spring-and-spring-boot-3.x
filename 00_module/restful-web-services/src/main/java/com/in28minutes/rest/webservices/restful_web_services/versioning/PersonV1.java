package com.in28minutes.rest.webservices.restful_web_services.versioning;

public class PersonV1 {

	private String name;

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "PersonV1{" + "name='" + name + '\'' + '}';
	}
}

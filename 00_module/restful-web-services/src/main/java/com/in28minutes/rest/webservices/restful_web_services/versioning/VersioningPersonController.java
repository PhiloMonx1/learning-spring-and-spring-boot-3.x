package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson(){
		return new PersonV1("김첨지");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson(){
		return new PersonV2(new Name("김", "첨지"));
	}

	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter(){
		return new PersonV1("이첨지");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter(){
		return new PersonV2(new Name("이", "첨지"));
	}

	@GetMapping(path = "/person", headers = "X_API_VERSION=1")
	public PersonV1 getFirstVersionOfPersonHeaders(){
		return new PersonV1("박첨지");
	}

	@GetMapping(path = "/person", headers = "X_API_VERSION=2")
	public PersonV2 getSecondVersionOfPersonHeaders(){
		return new PersonV2(new Name("박", "첨지"));
	}

	@GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader(){
		return new PersonV1("최첨지");
	}

	@GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader(){
		return new PersonV2(new Name("최", "첨지"));
	}
}

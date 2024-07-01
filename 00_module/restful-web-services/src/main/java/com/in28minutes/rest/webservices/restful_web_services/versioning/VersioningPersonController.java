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

}

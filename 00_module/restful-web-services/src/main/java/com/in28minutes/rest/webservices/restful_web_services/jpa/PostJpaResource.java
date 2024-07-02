package com.in28minutes.rest.webservices.restful_web_services.jpa;

import com.in28minutes.rest.webservices.restful_web_services.user.Post;
import com.in28minutes.rest.webservices.restful_web_services.user.User;
import com.in28minutes.rest.webservices.restful_web_services.user.UserNotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PostJpaResource {

	private PostRepository repository;

	public PostJpaResource(PostRepository repository) {
		this.repository = repository;
	}


	@GetMapping("/jpa/posts/{id}")
	public EntityModel<Post> retrievePost(@PathVariable int id) {
		Post post = repository.findById(id).orElse(null);
		if (post == null) {
			throw new RuntimeException("id:" + id);
		}

		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UserJpaResource.class).retrievePostsForUser(post.getUser().getId())
		);

		// HATEOAS
		EntityModel<Post> entityModel = EntityModel.of(post);
		entityModel.add(link.withRel("all-posts"));

		return entityModel;
	}
}

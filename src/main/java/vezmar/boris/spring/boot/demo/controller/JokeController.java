package vezmar.boris.spring.boot.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vezmar.boris.spring.boot.demo.model.Joke;
import vezmar.boris.spring.boot.demo.model.request.JokeRequest;
import vezmar.boris.spring.boot.demo.model.response.JokeResponse;
import vezmar.boris.spring.boot.demo.service.IJokeService;

@RestController
@RequestMapping("/jokes")
public class JokeController {

	@Autowired
	private IJokeService jokeService;

	@GetMapping
	public ResponseEntity<List<JokeResponse>> getAll(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		List<Joke> jokes = null;

		if (page == null || size == null) {
			jokes = jokeService.getAllBySortedLikeDislikeDifference();
		} else {
			jokes = jokeService.getPageBySortedLikeDislikeDifference(page, size);
		}
		List<JokeResponse> jokeResponses = jokes.stream()
				.map(joke -> new JokeResponse(joke.getId(), joke.getContent(), joke.getCategory().getName(),
						joke.getLikes(), joke.getDislikes(), joke.getLikes() - joke.getDislikes()))
				.collect(Collectors.toList());

		return new ResponseEntity<>(jokeResponses, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<JokeResponse> post(@ModelAttribute JokeRequest jokeRequest) {
		Joke joke = jokeService.create(jokeRequest.getContent(), jokeRequest.getCategory());

		JokeResponse jokeResponse = new JokeResponse(joke.getId(), joke.getContent(), joke.getCategory().getName(),
				joke.getLikes(), joke.getDislikes(), joke.getLikes() - joke.getDislikes());

		return new ResponseEntity<>(jokeResponse, HttpStatus.CREATED);
	}

	@PostMapping("/{id}/like")
	public ResponseEntity<Object> like(@PathVariable(value = "id", required = false) Integer id) {
		jokeService.like(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/{id}/dislike")
	public ResponseEntity<Object> dislike(@PathVariable(value = "id", required = false) Integer id) {
		jokeService.dislike(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		return new ResponseEntity<>(jokeService.count(), HttpStatus.OK);
	}
}

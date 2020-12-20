package vezmar.boris.spring.boot.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vezmar.boris.spring.boot.demo.model.Category;
import vezmar.boris.spring.boot.demo.model.Joke;
import vezmar.boris.spring.boot.demo.repository.CategoryRepository;
import vezmar.boris.spring.boot.demo.repository.JokeRepository;

@Service
public class JokeService implements IJokeService {

	@Autowired
	private JokeRepository jokeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Joke> getAllBySortedLikeDislikeDifference() {
		return jokeRepository.findAllBySortedLikeDislikeDifference();
	}

	@Override
	public List<Joke> getPageBySortedLikeDislikeDifference(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return jokeRepository.findPageBySortedLikeDislikeDifference(pageable);
	}

	@Override
	public Joke create(String content, String categoryName) {
		if (content == null) {
			throw new IllegalArgumentException("Content of joke to be created must not be null.");
		}
		if (content.isEmpty()) {
			throw new IllegalArgumentException("Content of joke to be created must not be empty.");
		}
		if (categoryName == null) {
			throw new IllegalArgumentException("Category of joke to be created must not be null.");
		}

		Category category = categoryRepository.findByName(categoryName);

		if (category == null) {
			throw new NoSuchElementException("Category specified by given name " + categoryName + " was not found");
		}

		Joke joke = new Joke(content, category);
		return jokeRepository.save(joke);
	}

	@Override
	public void like(Integer id) {
		Joke joke = jokeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Joke to like not found."));

		joke.setLikes(joke.getLikes() + 1);
		jokeRepository.save(joke);
	}

	@Override
	public void dislike(Integer id) {
		Joke joke = jokeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Joke to dislike not found."));
		;

		joke.setDislikes(joke.getDislikes() + 1);
		jokeRepository.save(joke);
	}

	@Override
	public Long count() {
		return jokeRepository.count();
	}

}

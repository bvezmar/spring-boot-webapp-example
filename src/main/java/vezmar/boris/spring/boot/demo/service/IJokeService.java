package vezmar.boris.spring.boot.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vezmar.boris.spring.boot.demo.model.Joke;

@Service
public interface IJokeService {

	List<Joke> getAllBySortedLikeDislikeDifference();
	
	List<Joke> getPageBySortedLikeDislikeDifference(int page, int size);
	
	Joke create(String content, String categoryName);
	
	@Transactional
	void like(Integer id);
	
	@Transactional
	void dislike(Integer id);
	
	Long count();
}

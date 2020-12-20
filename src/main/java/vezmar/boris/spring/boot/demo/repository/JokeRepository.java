package vezmar.boris.spring.boot.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vezmar.boris.spring.boot.demo.model.Joke;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer>{

	@Query("SELECT j, (j.likes-j.dislikes) AS difference FROM Joke j ORDER BY difference DESC")
	List<Joke> findAllBySortedLikeDislikeDifference();
	
	@Query("SELECT j, (j.likes-j.dislikes) AS difference FROM Joke j ORDER BY difference DESC")
	List<Joke> findPageBySortedLikeDislikeDifference(Pageable pageable);
}

package vezmar.boris.spring.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vezmar.boris.spring.boot.demo.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	Category findByName(String name);
}

package it.prova.poker.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.poker.model.User;

public interface UserRepository extends CrudRepository<User, Long>, QueryByExampleExecutor<User> {

	User findByUsername(String username);

	List<User> findAllByUsernameContaining(String term);
	
	@Query("select distinct u from User u left join fetch u.ruoli where u.id= ?1")
	User findWithRuoli(Long id);
	
	@Query("select distinct u from User u left join fetch u.tavolo_gioco left join fetch u.ruoli where u.id= ?1")
	User findWithGameAndRole(Long id);
}

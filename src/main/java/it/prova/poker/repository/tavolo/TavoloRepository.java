package it.prova.poker.repository.tavolo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.poker.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {

	@Query("select distinct t from Tavolo t join fetch t.user_creatore where t.id= ?1")
	Tavolo findWithCreatore(Long id);
	
	@Query("select distinct t from Tavolo t left join fetch t.users where t.id= ?1")
	Tavolo findWithPartecipanti(Long id);
}

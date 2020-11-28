package it.prova.poker.repository.tavolo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.poker.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {

}

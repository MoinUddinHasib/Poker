package it.prova.poker.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.poker.model.User;

public interface UserRepository extends CrudRepository<User, Long>, QueryByExampleExecutor<User> {

}

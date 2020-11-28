package it.prova.poker.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.poker.model.User;
import it.prova.poker.repository.user.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> listAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User caricaSingoloUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void inserisciNuovo(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void rimuovi(User user) {
		userRepository.delete(user);
	}

}

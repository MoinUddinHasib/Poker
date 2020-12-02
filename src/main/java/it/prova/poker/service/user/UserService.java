package it.prova.poker.service.user;

import java.util.List;

import it.prova.poker.model.User;

public interface UserService {
	
	public List<User> listAllUsers();

	public User caricaSingoloUser(Long id);

	public void aggiorna(User user);

	public void inserisciNuovo(User user);

	public void rimuovi(User user);
	
	public User caricaPerUsername(String username);

	public List<User> cercaByUsernameILike(String term);

	public List<User> findByExample(User user);

	public User caricaSingoloUserConRuoli(long id);
	
	public User caricaSingoloUserConPartitaERuoli(long id);

}

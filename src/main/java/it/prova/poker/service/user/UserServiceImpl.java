package it.prova.poker.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.poker.model.User;
import it.prova.poker.repository.user.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

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
		if(user.getId()==null || user.getNome()==null || user.getCognome()==null ||
				user.getUsername()==null || user.getPassword()==null ||
				user.getDataRegistrazione()==null || user.getStato()==null
				|| user.getEsperienzaAccumulata()==null || user.getCreditoAccumulato()==null) {
			System.err.println("Impossibile aggiornare lo user");
			System.exit(1);
		}
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void inserisciNuovo(User user) {
		if(user.getNome()==null || user.getCognome()==null ||
				user.getUsername()==null || user.getPassword()==null ||
				user.getDataRegistrazione()==null || user.getStato()==null
				|| user.getEsperienzaAccumulata()==null || user.getCreditoAccumulato()==null) {
			System.err.println("Impossibile inserire lo user");
			System.exit(1);
		}
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void rimuovi(User user) {
		if(user.getTavolo_gioco()!=null || user.getTavoli_creati().size()!=0) {
			System.err.println("Impossibile cancellare lo user");
			System.exit(1);
		}
		userRepository.delete(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User caricaPerUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> cercaByUsernameILike(String term) {
		return userRepository.findAllByUsernameContaining(term);
	}

	@Override
	public List<User> findByExample(User example) {
		String query = "select distinct u from User u left join fetch u.ruoli r where u.id = u.id ";

		if (StringUtils.isNotEmpty(example.getNome()))
			query += " and u.nome like '%" + example.getNome()+"%'";
		if (StringUtils.isNotEmpty(example.getCognome()))
			query += " and u.cognome like '%" + example.getCognome() + "%' ";
		if (StringUtils.isNotEmpty(example.getUsername()))
			query += " and u.username like '%" + example.getUsername() + "%' ";
		if (example.getDataRegistrazione()!=null)
			query += " and u.dataRegistrazione = '" + example.getDataRegistrazione()+"'";
		if (example.getStato()!=null)
			query += " and u.stato = '" + example.getStato()+"'";
		if (!example.getRuoli().isEmpty())
			query += " and r.id = " + example.getRuoli().iterator().next().getId();

		return entityManager.createQuery(query, User.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public User caricaSingoloUserConRuoli(long id) {
		return userRepository.findWithRuoli(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User caricaSingoloUserConPartita(long id) {
		return userRepository.findWithGame(id);
	}

}

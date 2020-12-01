package it.prova.poker.service.ruolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.poker.model.Ruolo;
import it.prova.poker.repository.ruolo.RuoloRepository;

@Component
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ruolo> listAllRuoli() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ruolo caricaSingoloRuolo(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Ruolo ruolo) {
		if(ruolo.getId()==null || ruolo.getTipo()==null ) {
			System.err.println("Impossibile aggiornare il ruolo");
			System.exit(1);
		}
		ruoloRepository.save(ruolo);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Ruolo ruolo) {
		if(ruolo.getTipo()==null) {
			System.err.println("Impossibile inserire il ruolo");
			System.exit(1);
		}
		ruoloRepository.save(ruolo);
	}

	@Override
	@Transactional
	public void rimuovi(Ruolo ruolo) {
		if(ruolo.getUsers().size()!=0) {
			System.err.println("Impossibile rimuovere il ruolo perchè ci sono user collegati");
			System.exit(1);
		}
		ruoloRepository.delete(ruolo);
	}


}

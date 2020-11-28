package it.prova.poker.service.tavolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.poker.model.Tavolo;
import it.prova.poker.repository.tavolo.TavoloRepository;

@Component
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> listAllTavoli() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo caricaSingoloTavolo(Long id) {
		return tavoloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Tavolo tavolo) {
		tavoloRepository.save(tavolo);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Tavolo tavolo) {
		tavoloRepository.save(tavolo);
	}

	@Override
	@Transactional
	public void rimuovi(Tavolo tavolo) {
		tavoloRepository.delete(tavolo);
	}

}

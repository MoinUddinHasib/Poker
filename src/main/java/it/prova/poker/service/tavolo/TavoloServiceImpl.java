package it.prova.poker.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.poker.model.Tavolo;
import it.prova.poker.repository.tavolo.TavoloRepository;

@Component
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository tavoloRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
		if(tavolo.getId()==null || tavolo.getEsperienzaMin()==null || tavolo.getCifraMin()==null
				|| tavolo.getDenominazione()==null || tavolo.getDataCreazione()==null
				|| tavolo.getUser_creatore()==null) {
			System.err.println("Impossibile aggiornare il tavolo");
			System.exit(1);
		}
		tavoloRepository.save(tavolo);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Tavolo tavolo) {
		if(tavolo.getEsperienzaMin()==null || tavolo.getCifraMin()==null
				|| tavolo.getDenominazione()==null || tavolo.getDataCreazione()==null
				|| tavolo.getUser_creatore()==null) {
			System.err.println("Impossibile inserire il tavolo");
			System.exit(1);
		}
		tavoloRepository.save(tavolo);
	}

	@Override
	@Transactional
	public void rimuovi(Tavolo tavolo) {
		if(tavolo.getUsers().size()!=0) {
			System.err.println("Impossibile rimuovere il tavolo perchè ci sono giocatori");
			System.exit(1);
		}
		tavoloRepository.delete(tavolo);
	}
	
	@Override
	@Transactional
	public List<Tavolo> findByExample(Tavolo example) {
		String query = "from Tavolo t left join fetch t.users u left join fetch t.user_creatore where t.esperienzaMin <= "+example.getEsperienzaMin();

		if (example.getCifraMin()!=null)
			query += " and t.cifraMin >= " + example.getCifraMin();
		if (StringUtils.isNotEmpty(example.getDenominazione()))
			query += " and t.denominazione like '%" + example.getDenominazione() + "%' ";
		if (example.getDataCreazione() != null)
			query += " and t.dataCreazione = '" + example.getDataCreazione()+"'";
		if (!example.getUsers().isEmpty())
			query += " and u.id = " + example.getUsers().iterator().next().getId();
		if (example.getUser_creatore()!=null)
			query += " and t.user_creatore.id = " + example.getUser_creatore().getId();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

	@Override
	@Transactional
	public List<Tavolo> findByExample2(Tavolo example) {
		String query = "from Tavolo t join fetch t.user_creatore where t.user_creatore.id = "+example.getUser_creatore().getId();

		if (StringUtils.isNotEmpty(example.getDenominazione()))
			query += " and t.denominazione like '%" + example.getDenominazione() + "%' ";
		if (example.getDataCreazione() != null)
			query += " and t.dataCreazione = '" + example.getDataCreazione()+"'";
		if (example.getCifraMin()!=null)
			query += " and t.cifraMin >= " + example.getCifraMin();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo caricaSingoloTavoloConCreatore(Long id) {
		return tavoloRepository.findWithCreatore(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo caricaSingoloTavoloConPartecipanti(Long id) {
		return tavoloRepository.findWithPartecipanti(id);
	}

}

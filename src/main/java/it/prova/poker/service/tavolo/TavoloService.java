package it.prova.poker.service.tavolo;

import java.util.List;

import it.prova.poker.model.Tavolo;

public interface TavoloService {

	public List<Tavolo> listAllTavoli();

	public Tavolo caricaSingoloTavolo(Long id);

	public void aggiorna(Tavolo tavolo);

	public void inserisciNuovo(Tavolo tavolo);

	public void rimuovi(Tavolo tavolo);
	
	public List<Tavolo> findByExample(Tavolo tavolo);

	public List<Tavolo> findByExample2(Tavolo tavolo);

	public Tavolo caricaSingoloTavoloConCreatore(Long id);
	
	public Tavolo caricaSingoloTavoloConPartecipanti(Long id);
}

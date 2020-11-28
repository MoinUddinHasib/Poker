package it.prova.poker.service.tavolo;

import java.util.List;

import it.prova.poker.model.Tavolo;

public interface TavoloService {

	public List<Tavolo> listAllTavoli();

	public Tavolo caricaSingoloTavolo(Long id);

	public void aggiorna(Tavolo tavolo);

	public void inserisciNuovo(Tavolo tavolo);

	public void rimuovi(Tavolo tavolo);
}

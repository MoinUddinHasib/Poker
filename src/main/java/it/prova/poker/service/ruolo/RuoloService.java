package it.prova.poker.service.ruolo;

import java.util.List;

import it.prova.poker.model.Ruolo;

public interface RuoloService {

	public List<Ruolo> listAllRuoli();

	public Ruolo caricaSingoloRuolo(Long id);

	public void aggiorna(Ruolo ruolo);

	public void inserisciNuovo(Ruolo ruolo);

	public void rimuovi(Ruolo ruolo);
}

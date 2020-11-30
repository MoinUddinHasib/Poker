package it.prova.poker.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.prova.poker.model.Tavolo;
import it.prova.poker.model.User;

public class TavoloDTO {
	
	private Long id;
	private String esperienzaMin;
	private String cifraMin;
	private String denominazione;
	private User user_creatore;

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(String esperienzaMin, String cifraMin, String denominazione,User user_creatore) {
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.user_creatore = user_creatore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(String esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public String getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(String cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public User getUser_creatore() {
		return user_creatore;
	}

	public void setUser_creatore(User user_creatore) {
		this.user_creatore = user_creatore;
	}
	
	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(!StringUtils.isNumeric(this.esperienzaMin) || StringUtils.isBlank(this.esperienzaMin))
			result.add("Il campo esperienza minima non è valido");
		if(!StringUtils.isNumeric(this.cifraMin) || StringUtils.isBlank(this.cifraMin))
			result.add("Il campo puntata minima non è valido");
		if(StringUtils.isBlank(this.denominazione))
			result.add("Il campo denominazione non può essere vuoto");
		return result;
	}

	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		return new Tavolo(Integer.parseInt(tavoloDTO.getEsperienzaMin()),
				Integer.parseInt(tavoloDTO.getCifraMin()),
				tavoloDTO.getDenominazione(),
				tavoloDTO.getUser_creatore());
	}

}

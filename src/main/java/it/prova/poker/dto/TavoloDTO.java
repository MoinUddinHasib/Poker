package it.prova.poker.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import it.prova.poker.model.User;

public class TavoloDTO {
	
	private Long id;
	private Integer esperienzaMin;
	private Integer cifraMin;
	private String denominazione;
	private LocalDate dataCreazione;
	private Set<User> users= new HashSet<>();
	private User user_creatore;

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione,
			User user, User user_creatore) {
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.users.add(user);
		this.user_creatore = user_creatore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(Integer esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public Integer getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(Integer cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public User getUser_creatore() {
		return user_creatore;
	}

	public void setUser_creatore(User user_creatore) {
		this.user_creatore = user_creatore;
	}
	/*
	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
		return result;
	}

	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		User u=new User(userDTO.nome,userDTO.cognome,userDTO.username,userDTO.password);
		u.setId(userDTO.getId());
		return u;
	}
*/
}

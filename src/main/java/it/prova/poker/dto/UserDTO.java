package it.prova.poker.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.prova.poker.model.User;
import it.prova.poker.model.User.Stato;

public class UserDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String stato;
	private String data;
	private String ruoloId;
	
	public UserDTO() {
	}

	public UserDTO(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	public UserDTO(String nome, String cognome, String username, String data, String stato, String ruoloId) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.data = data;
		this.stato = stato;
		this.ruoloId = ruoloId;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRuoloId() {
		return ruoloId;
	}

	public void setRuoloId(String ruoloId) {
		this.ruoloId = ruoloId;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	
	public List<String> errorsSearch(){
		List<String> result = new ArrayList<String>();
		if(nome == null)
			result.add("Il campo nome non è valido");
		if(cognome == null)
			result.add("Il campo cognome non è valido");
		if(username == null)
			result.add("Il campo username non è valido");
		if(data == null || (!data.isEmpty() && isNaD(data)))
			result.add("Il campo data non è valido");
		if(stato == null || (!stato.isEmpty() && isNaS(stato)))
			result.add("Il campo stato non è valido");
		if(ruoloId == null || (!ruoloId.isEmpty() && !StringUtils.isNumeric(ruoloId)))
			result.add("Il campo ruolo non è valido");
		return result;
	}
	
	private boolean isNaS(String stato) {
		try {
			Stato.valueOf(stato);
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private boolean isNaD(String data) {
		try {
			LocalDate.parse(data);
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public static User buildModelFromDto(UserDTO userDTO) {
		User u=new User(userDTO.nome,userDTO.cognome,userDTO.username,userDTO.password);
		u.setId(userDTO.getId());
		return u;
	}

}

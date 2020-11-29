package it.prova.poker.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tavolo")
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "esperienza_min")
	private Integer esperienzaMin;
	@Column(name = "cifra_min")
	private Integer cifraMin;
	@Column(name = "denominazione")
	private String denominazione;
	
	@Column(name = "data_creazione")
	private LocalDate dataCreazione;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo_gioco")
	private Set<User> users= new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk_creatore")
	private User user_creatore;

	public Tavolo() {
		super();
	}

	public Tavolo(Long id, Integer esperienzaMin, Integer cifraMin, String denominazione, User user_creatore) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.user_creatore = user_creatore;
		
		String data=LocalDate.now().toString();
		String[] dat=data.split("-");
		dat[2]=String.valueOf(Integer.parseInt(dat[2])+1);
		dataCreazione = LocalDate.parse(dat[0]+"-"+dat[1]+"-"+dat[2]);
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

	@Override
	public String toString() {
		return "Tavolo [id=" + id + ", esperienzaMin=" + esperienzaMin + ", cifraMin=" + cifraMin + ", denominazione="
				+ denominazione + ", dataCreazione=" + dataCreazione + ", users=" + users.size() + ", user_creatore="
				+ user_creatore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cifraMin;
		result = prime * result + ((denominazione == null) ? 0 : denominazione.hashCode());
		result = prime * result + esperienzaMin;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (cifraMin != other.cifraMin)
			return false;
		if (denominazione == null) {
			if (other.denominazione != null)
				return false;
		} else if (!denominazione.equals(other.denominazione))
			return false;
		if (esperienzaMin != other.esperienzaMin)
			return false;
		return true;
	}

}

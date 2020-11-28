package it.prova.poker.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tavolo")
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "esperienza_min")
	private int esperienzaMin;
	@Column(name = "cifra_min")
	private int cifraMin;
	@Column(name = "denominazione")
	private String denominazione;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_registrazione")
	private Date dataRegistrazione;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo_gioco")
	private Set<User> users= new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk_creatore")
	private User user_creatore;

	public Tavolo() {
		super();
	}

	public Tavolo(Long id, int esperienzaMin, int cifraMin, String denominazione, Date dataRegistrazione,
			User user_creatore) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataRegistrazione = dataRegistrazione;
		this.user_creatore = user_creatore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(int esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public int getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(int cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
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
				+ denominazione + ", dataRegistrazione=" + dataRegistrazione + ", users=" + users.size() + ", user_creatore="
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

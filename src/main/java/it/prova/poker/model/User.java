package it.prova.poker.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	public enum Stato{
		CREATO,
		ATTIVO,
		INATTIVO
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	@Column(name = "data_registrazione")
	private LocalDate dataRegistrazione;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "stato")
	private Stato stato;
	
	@Column(name = "esperienza_accumulata")
	private Integer esperienzaAccumulata;
	@Column(name = "credito_accumulato")
	private Integer creditoAccumulato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavolo_fk_gioco")
	private Tavolo tavolo_gioco;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user_creatore")
	private Set<Tavolo> tavoli_creati= new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_ruolo",
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "id"))
	private Set<Ruolo> ruoli= new HashSet<>();

	public User() {
		super();
	}

	public User(String nome, String cognome, String username, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		dataRegistrazione = LocalDate.now();
		esperienzaAccumulata = 0;
		creditoAccumulato  = 0;
		stato = Stato.CREATO;
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

	public LocalDate getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(LocalDate dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public Integer getEsperienzaAccumulata() {
		return esperienzaAccumulata;
	}

	public void setEsperienzaAccumulata(Integer esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
	}

	public Integer getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(Integer creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public Tavolo getTavolo_gioco() {
		return tavolo_gioco;
	}

	public void setTavolo_gioco(Tavolo tavolo_gioco) {
		this.tavolo_gioco = tavolo_gioco;
	}

	public Set<Tavolo> getTavoli_creati() {
		return tavoli_creati;
	}

	public void setTavoli_creati(Set<Tavolo> tavoli_creati) {
		this.tavoli_creati = tavoli_creati;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password="
				+ password + ", dataRegistrazione=" + dataRegistrazione + ", stato=" + stato + ", esperienzaAccumulata="
				+ esperienzaAccumulata + ", creditoAccumulato=" + creditoAccumulato + ", tavolo_gioco=" + tavolo_gioco
				+ ", tavoli_creati=" + tavoli_creati.size() + ", ruoli=" + ruoli.size() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}

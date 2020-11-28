package it.prova.poker.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {
	
	public enum Tipo{
		ADMIN_ROLE,
		SPECIAL_PLAYER_ROLE,
		PLAYER_ROLE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private Tipo tipo;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ruoli")
	private Set<User> users= new HashSet<>();
	
	public Ruolo() {
		super();
	}

	public Ruolo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", tipo=" + tipo + ", users=" + users.size() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Ruolo other = (Ruolo) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	
}



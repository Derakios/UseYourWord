package fr.formation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="partie")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAR_TYPE")
public class Partie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PAR_ID")
	private int id;
	
	@OneToMany(mappedBy = "partie",fetch = FetchType.EAGER)
	private Set<Equipe> listeEquipes;
	
	@OneToMany(mappedBy = "partie",fetch = FetchType.EAGER)
	private Set<Manche> listeManches;

	public Partie() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Equipe> getListeEquipes() {
		return listeEquipes;
	}

	public void setListeEquipes(Set<Equipe> listeEquipes) {
		this.listeEquipes = listeEquipes;
	}

	public Set<Manche> getListeManches() {
		return listeManches;
	}

	public void setListeManches(Set<Manche> listeManches) {
		this.listeManches = listeManches;
	}
	
}

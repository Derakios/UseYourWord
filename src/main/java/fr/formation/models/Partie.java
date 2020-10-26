package fr.formation.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="partie")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAR_TYPE")
public class Partie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PAR_ID")
	private int id;
	
	@OneToMany
	@JoinColumn(name = "equipe_fk")
	private List<Equipe> listeEquipes = new ArrayList<Equipe>();
	
	@OneToMany
	@JoinColumn(name = "manche_fk")
	private List<Manche> listeManches = new ArrayList<Manche>();

	public Partie() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Equipe> getListeEquipes() {
		return listeEquipes;
	}

	public void setListeEquipes(List<Equipe> listeEquipes) {
		this.listeEquipes = listeEquipes;
	}

	public List<Manche> getListeManches() {
		return listeManches;
	}

	public void setListeManches(List<Manche> listeManches) {
		this.listeManches = listeManches;
	}
	
}

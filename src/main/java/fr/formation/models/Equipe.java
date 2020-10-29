package fr.formation.models;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="equipe")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EQU_TYPE")
@Transactional
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EQU_ID")
	private int id;
	
	@Column(name="EQU_NOM")
	private String nom;
	
	@Column(name="EQU_NBJOUEURS")
	private int nbJoueurs;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EQU_PARTIE_ID")
	private Partie partie;
	
	@OneToMany(mappedBy = "equipe",fetch = FetchType.EAGER)
	private List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	
	@OneToMany(mappedBy = "equipe",fetch = FetchType.EAGER)
	private List<Reponse> listeReponses = new ArrayList<Reponse>();
	
	public Equipe() {
		
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(List<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public List<Reponse> getListeReponses() {
		return listeReponses;
	}

	public void setListeReponses(List<Reponse> listeReponses) {
		this.listeReponses = listeReponses;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}
	
}

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
@Table(name="equipe")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EQU_TYPE")
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EQU_ID")
	private int id;
	
	@OneToMany
	@JoinColumn(name = "joueurs_fk")
	private List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	
	@OneToMany
	@JoinColumn(name = "reponses_fk")
	private List<Reponse> listeReponses = new ArrayList<Reponse>();
	
	public Equipe() {
		
	}
	
}

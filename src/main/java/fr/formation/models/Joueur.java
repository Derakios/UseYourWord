package fr.formation.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="joueur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "JOU_TYPE")
public class Joueur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="JOU_ID")
	private int id;
	
	@Column(name="JOU_PSEUDO")
	private String pseudo;
	
	@Column(name="JOU_ROLE")
	private String role;
	
	@Column(name="JOU_MDP")
	private String mdp;
	
	
	public Joueur() {
		
	}
	
}

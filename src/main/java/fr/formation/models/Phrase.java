package fr.formation.models;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="phrase")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PHR_TYPE")
@Transactional
public class Phrase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PHR_ID")
	private int id;
	
	@Column(name="PHR_TEXTE")
	private String texte;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PHR_MANCHE_ID")
	private Manche manche;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTexte() {
		return texte;
	}


	public void setTexte(String texte) {
		this.texte = texte;
	}


	public Phrase() {
		
	}
	
}

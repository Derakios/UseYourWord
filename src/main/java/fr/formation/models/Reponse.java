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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="reponse")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "REP_TYPE")
@Transactional
public class Reponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="REP_ID")
	private int id;
	
	@Column(name="REP_TEXTE")
	private String texte;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REP_EQUIPE_ID")
	private Equipe equipe;
	
	public Reponse() {
		
	}

}

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="manche")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MAN_TYPE")
@Transactional
public class Manche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MAN_ID")
	private int id;
	
	@OneToOne(mappedBy = "manche",fetch = FetchType.EAGER)
	private Phrase phrase;
	
	@OneToOne(mappedBy = "manche",fetch = FetchType.EAGER)
	private Image image;
	
	@OneToOne(mappedBy = "manche",fetch = FetchType.EAGER)
	private Video video;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAN_PARTIE_ID")
	private Partie partie;
	
	@Column(name="MAN_TEMPS")
	private int temps;

	public Manche() {
		
	}
	
}

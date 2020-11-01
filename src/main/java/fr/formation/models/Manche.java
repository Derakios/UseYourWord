package fr.formation.models;

import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "manche",fetch = FetchType.EAGER)
	private Set<Reponse> listeReponse = new HashSet<Reponse>();
	
	@Column(name="MAN_TEMPS")
	private int temps;

	public Manche() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Phrase getPhrase() {
		return phrase;
	}

	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public Set<Reponse> getListeReponse() {
		return listeReponse;
	}

	public void setListeReponse(Set<Reponse> listeReponse) {
		this.listeReponse = listeReponse;
	}
	
}

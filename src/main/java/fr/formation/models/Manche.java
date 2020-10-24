package fr.formation.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="manche")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MAN_TYPE")
public class Manche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MAN_ID")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "phrase_fk")
	private Phrase phrase;
	
	@OneToOne
	@JoinColumn(name = "image_fk")
	private Image image;
	
	@OneToOne
	@JoinColumn(name = "video_fk")
	private Video video;
	
	
	@Column(name="MAN_TEMPS")
	private int temps;

	public Manche() {
		
	}
	
}

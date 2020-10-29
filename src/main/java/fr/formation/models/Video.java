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
@Table(name="video")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VID_TYPE")
@Transactional
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VID_ID")
	private int id;
	
	@Column(name="VID_LIENVIDEO")
	private String lienVideo;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VID_MANCHE_ID")
	private Manche manche;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLienVideo() {
		return lienVideo;
	}


	public void setLienVideo(String lienVideo) {
		this.lienVideo = lienVideo;
	}


	public Video() {
		
	}
	
}

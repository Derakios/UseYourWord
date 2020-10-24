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
@Table(name="video")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VID_TYPE")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VID_ID")
	private int id;
	
	@Column(name="VID_LIENVIDEO")
	private String lienVideo;
	
	
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

package fr.formation.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IEquipeRepository;
import fr.formation.dao.IImageRepository;
import fr.formation.dao.IJoueurRepository;
import fr.formation.dao.IPartieRepository;
import fr.formation.dao.IPhraseRepository;
import fr.formation.dao.IVideoRepository;
import fr.formation.models.Equipe;
import fr.formation.models.Image;
import fr.formation.models.Joueur;
import fr.formation.models.Partie;
import fr.formation.models.Phrase;
import fr.formation.models.Video;

@Service
public class PartieManager {
	
	@Autowired
	private IPartieRepository daoPartie;
	@Autowired
	private IImageRepository daoImage;
	@Autowired
	private IVideoRepository daoVideo;
	@Autowired
	private IPhraseRepository daoPhrase;
	@Autowired
	private IEquipeRepository daoEquipe;
	@Autowired
	private IJoueurRepository daoJoueur;
	
	public void addDonnees(String lien, String type) {
		
		switch (type) {
			case "image":
				Image img = new Image();
				img.setLienImage(lien);
				this.daoImage.save(img);
			break;
			case "video":
				Video video = new Video();
				video.setLienVideo(lien);
				this.daoVideo.save(video);
				break;
			case "phrase":
				Phrase phrase = new Phrase();
				phrase.setTexte(lien);
				this.daoPhrase.save(phrase);
				break;
		}
	}
	
	public void addEquipe(Equipe equipe) {
		this.daoEquipe.save(equipe);
	}
	
	public List<Equipe> getAllEquipe() {
		return this.daoEquipe.findAll();
	}
	
	public List<Image> getAllImage() {
		return this.daoImage.findAll();
	}
	
	public List<Video> getAllVideo() {
		return this.daoVideo.findAll();
	}
	
	public List<Phrase> getAllPhrase() {
		return this.daoPhrase.findAll();
	}
	
	public void add(Partie partie) {
		this.daoPartie.save(partie);
	}
	
	public void add(Joueur joueur) {
		this.daoJoueur.save(joueur);
	}
	
	public List<Partie> findAllPartie(){
		return this.daoPartie.findAll();
	}
	
	@Transactional
	public void modify(int id,Partie partie) {

		this.daoPartie.save(partie);
	}
	
	public void delete(int id) {
		//Piece piece = pieces.stream().filter(c -> c.getId() == id).findFirst().get();
		//pieces.remove(piece);
		this.daoPartie.deleteById(id);
	}
	
	public Partie get(int id) throws Exception {
		return this.daoPartie.findById(id).orElseThrow(Exception::new);
	}

}

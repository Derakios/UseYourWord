package fr.formation.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.models.Equipe;
import fr.formation.models.Image;
import fr.formation.models.Joueur;
import fr.formation.models.Manche;
import fr.formation.models.Partie;
import fr.formation.models.Phrase;
import fr.formation.models.Reponse;
import fr.formation.models.Video;

@Controller
public class PartieController {
	
	@Autowired
	private PartieManager srvPartie;
	
	@GetMapping
	public String home(Model model,@RequestParam(required=false,defaultValue="0") int id) throws Exception {

		return "home";
	}
	
	@GetMapping("/equipe")
	public String equipe(){
		return "creationEquipe";
	}
	
	@PostMapping("/equipe")
	public String equipe(@RequestParam Map<String,String> contenu, Model model){
		Partie partie = new Partie();
		this.srvPartie.add(partie);
		
		List<Equipe> listeEquipe = new ArrayList<Equipe>();
		
		String nomE1 = contenu.get("team1");
		Equipe E1 = new Equipe();
		E1.setNom(nomE1);
		E1.setNbJoueurs(Integer.parseInt(contenu.get("listNbJ1")));
		E1.setPartie(partie);
		this.srvPartie.addEquipe(E1);
		listeEquipe.add(E1);
		String nomE2 = contenu.get("team2");
		Equipe E2 = new Equipe();
		E2.setNbJoueurs(Integer.parseInt(contenu.get("listNbJ2")));
		E2.setNom(nomE2);
		E2.setPartie(partie);
		this.srvPartie.addEquipe(E2);
		listeEquipe.add(E2);
		String nomE3 = contenu.get("team3");
		if(nomE3 != null) {
			if(!nomE3.equals("")) {
				Equipe E3 = new Equipe();
				E3.setNom(nomE1);
				E3.setNbJoueurs(Integer.parseInt(contenu.get("listNbJ3")));
				E3.setPartie(partie);
				this.srvPartie.addEquipe(E3);
				listeEquipe.add(E3);
			}
		}
		
		for(Equipe e : listeEquipe) {
			partie.getListeEquipes().add(e);
		}
		
		this.srvPartie.modify(partie.getId(),partie);
		
		return "redirect:/joueur?id="+partie.getId();
	}
	
	@GetMapping("/joueur")
	public String joueur(Model model, @RequestParam int id) throws Exception{
		Partie partie = this.srvPartie.get(id);
		Set<Equipe> equipes = partie.getListeEquipes();
		model.addAttribute("listeEquipes",equipes);
		model.addAttribute("id",partie.getId());
		
		return "CreationJoueur";
	}
	
	@PostMapping("/joueur")
	public String joueur(@RequestParam Map<String,String> contenu, @RequestParam int id, Model model) throws Exception{
		
		Partie partie = this.srvPartie.get(id);
		
		Set<Equipe> listeEquipe = partie.getListeEquipes();
		
		for (Map.Entry<String, String> entry : contenu.entrySet()) {
			String cle = entry.getKey();
			String value = entry.getValue();
			String[] compoCle = cle.split("/");
			
			if(compoCle.length > 1) {
				Joueur joueur = new Joueur();
				joueur.setPseudo(value);
				for(Equipe e : listeEquipe) {
					if(e.getNom().equals(compoCle[1])) {
						joueur.setEquipe(e);
						this.srvPartie.add(joueur);
						e.getListeJoueurs().add(joueur);
						this.srvPartie.modify(e.getId(), e);
					}
				}
			}
	    }
		partie.setListeEquipes(listeEquipe);
		this.srvPartie.modify(id, partie);
		
		return "redirect:/partie?id="+id+"&manche=1";
	}
	
	@GetMapping("/partie")
	public String partie(Model model, @RequestParam int id, @RequestParam int manche) throws Exception{
		
		Partie partie = this.srvPartie.get(id);
		Set<Equipe> listeEquipe = partie.getListeEquipes();
		ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
		
		for(Equipe e : listeEquipe) {
			Set<Joueur> liste = e.getListeJoueurs();
			for(Joueur j : liste) {
				listeJoueur.add(j);
			}
		}
		
		model.addAttribute("manche",manche);
		model.addAttribute("listeJoueur",listeJoueur);
		
		Manche mancheJeu = new Manche();
		String donnee = "";
		Random rand = new Random(); 
		int nombreAleatoire;
		Set<Manche> listeManche = partie.getListeManches();
		
		switch(manche) {
			case 1 :
				ArrayList<Phrase> listePhrase = (ArrayList<Phrase>) this.srvPartie.getAllPhrase();
				nombreAleatoire = rand.nextInt(listePhrase.size());
				Phrase phrase = listePhrase.get(nombreAleatoire);
				donnee = phrase.getTexte();
				
				mancheJeu.setPhrase(phrase);
				mancheJeu.setPartie(partie);
				this.srvPartie.add(mancheJeu);
				listeManche.add(mancheJeu);
				this.srvPartie.modify(id,partie);
				
				break;
			case 2 :
				ArrayList<Image> listeImage = (ArrayList<Image>) this.srvPartie.getAllImage();
				nombreAleatoire = rand.nextInt(listeImage.size());
				Image image = listeImage.get(nombreAleatoire);
				donnee = image.getLienImage();
				
				mancheJeu.setImage(image);
				mancheJeu.setPartie(partie);
				this.srvPartie.add(mancheJeu);
				listeManche.add(mancheJeu);
				this.srvPartie.modify(id,partie);
				break;
			case 3 :
				ArrayList<Video> listeVideo = (ArrayList<Video>) this.srvPartie.getAllVideo();
				nombreAleatoire = rand.nextInt(listeVideo.size());
				Video video = listeVideo.get(nombreAleatoire);
				donnee = video.getLienVideo();
				
				mancheJeu.setVideo(video);
				mancheJeu.setPartie(partie);
				this.srvPartie.add(mancheJeu);
				listeManche.add(mancheJeu);
				this.srvPartie.modify(id,partie);
				break;
		}
		
		model.addAttribute("donnee",donnee);
		
		return "partie";
	}
	
	@PostMapping("/partie")
	public String partie(@RequestParam Map<String,String> contenu, @RequestParam int id, @RequestParam int manche, Model model) throws Exception{
		
		Partie partie = this.srvPartie.get(id);
		
		Set<Equipe> listeEquipe = partie.getListeEquipes();
		Set<Manche> listeManche = partie.getListeManches();
		Manche mancheJeu = null;
		int cpt = 0;
		
		for (Iterator<Manche> it = listeManche.iterator(); it.hasNext(); ) {
	        Manche m = it.next();
	        if(mancheJeu == null) {
	        	mancheJeu = m;
	        }
	        if(m.getId() > mancheJeu.getId()) {
	        	mancheJeu = m;
	        }
	    }
		
		for (Map.Entry<String, String> entry : contenu.entrySet()) {
			String cle = entry.getKey();
			String value = entry.getValue();
			String[] compoCle = cle.split("/");
			
			if(compoCle.length > 1) {
				Reponse reponse = new Reponse();
				for(Equipe e : listeEquipe) {
					if(e.getNom().equals(compoCle[1])) {
						reponse.setEquipe(e);
						reponse.setManche(mancheJeu);
						reponse.setTexte(value);
						this.srvPartie.add(reponse);
						e.getListeReponses().add(reponse);
						mancheJeu.getListeReponse().add(reponse);
						this.srvPartie.modify(mancheJeu.getId(), mancheJeu);
						this.srvPartie.modify(e.getId(), e);
					}
				}
			}
	    }
		partie.setListeEquipes(listeEquipe);
		partie.setListeManches(listeManche);
		this.srvPartie.modify(id, partie);
		
		return "redirect:/votes?id="+id+"&manche="+manche;
	}
	
	@GetMapping("/votes")
	public String votes(Model model, @RequestParam int id, @RequestParam int manche) throws Exception{
		
		Partie partie = this.srvPartie.get(id);
		int nbJoueur = 0;
		Set<Equipe> listeEquipe = partie.getListeEquipes();
		for(Equipe e : listeEquipe) {
			nbJoueur = nbJoueur+e.getNbJoueurs();
		}
		Set<Manche> listeManche = partie.getListeManches();
		
		Manche mancheJeu = null;
		int cpt = 0;
		
		for (Iterator<Manche> it = listeManche.iterator(); it.hasNext(); ) {
	        Manche m = it.next();
	        if(mancheJeu == null) {
	        	mancheJeu = m;
	        }
	        if(m.getId() > mancheJeu.getId()) {
	        	mancheJeu = m;
	        }
	    }
		
		Set<Reponse> listeReponse = mancheJeu.getListeReponse();
		
		model.addAttribute("listeReponse",listeReponse);
		model.addAttribute("nbJoueur",nbJoueur);
		
		return "votes";
	}
	
	@PostMapping("/votes")
	public String votes(@RequestParam Map<String,String> contenu, @RequestParam int id, @RequestParam int manche, Model model) throws Exception{
		
		Partie partie = this.srvPartie.get(id);
		
		Set<Equipe> listeEquipe = partie.getListeEquipes();
		Set<Manche> listeManche = partie.getListeManches();
		
		Equipe equipe = null;
		int voteMax = -1;
		
		for (Map.Entry<String, String> entry : contenu.entrySet()) {
			String cle = entry.getKey();
			String value = entry.getValue();
			String[] compoCle = cle.split("/");
			
			if(compoCle.length > 1) {
				
				for(Equipe e : listeEquipe) {
					if(e.getNom().equals(compoCle[1])) {
						for(Reponse r : e.getListeReponses()) {
							if(r.getId() == Integer.parseInt(compoCle[2])) {
								if(Integer.parseInt(value)>voteMax) {
									voteMax = Integer.parseInt(value);
									equipe = e;
								}
								r.setNbVote(Integer.parseInt(value));
								this.srvPartie.modify(r.getId(), r);
							}
						}
					}
				}
			}
	    }
		
		for(Equipe e : listeEquipe) {
			if(e.getId() == equipe.getId()) {
				e.setScore(e.getScore()+1);
				this.srvPartie.modify(e.getId(), e);
			}
		}
		
		partie.setListeEquipes(listeEquipe);
		this.srvPartie.modify(id, partie);
		
		manche = manche+1;
		
		if(manche < 4) {
			return "redirect:/partie?id="+id+"&manche="+manche;
		}
		else {
			return "redirect:/fin?id="+id;
		}
	}
	
	@GetMapping("/fin")
	public String fin(Model model, @RequestParam int id) throws Exception{
		Partie partie = this.srvPartie.get(id);
		Set<Equipe> listeEquipe = partie.getListeEquipes();
		Equipe gagnant = null;
		
		for(Equipe e : listeEquipe) {
			if(gagnant == null) {
				gagnant = e;
			}
			else if(gagnant.getScore() < e.getScore()) {
				gagnant = e;
			}
		}
		
		model.addAttribute("gagnant",gagnant.getNom());
		
		return "Fin";
	}
	
	@GetMapping("/ajoutDonnees")
	public String ajoutDonnees(Model model){
		return "ajouterDonnee";
	}
	@PostMapping("/ajoutDonnees")
	public String ajoutDonnees(@RequestParam Map<String,String> contenu) {
		String type = contenu.get("selectDonnee");
		String value = contenu.get("donnee");
		
		this.srvPartie.addDonnees(value, type);
		return "redirect:/";
	}

}

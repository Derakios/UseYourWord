package fr.formation.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.models.Equipe;
import fr.formation.models.Partie;

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
		
		ArrayList<Equipe> listeEquipe = new ArrayList<Equipe>();
		
		String nomE1 = contenu.get("team1");
		Equipe E1 = new Equipe();
		E1.setNom(nomE1);
		this.srvPartie.addEquipe(E1);
		String nomE2 = contenu.get("team2");
		Equipe E2 = new Equipe();
		E2.setNom(nomE2);
		this.srvPartie.addEquipe(E2);
		String nomE3 = contenu.get("team3");
		Equipe E3 = new Equipe();
		E3.setNom(nomE1);
		this.srvPartie.addEquipe(E3);
		
		listeEquipe.add(E1);
		listeEquipe.add(E2);
		listeEquipe.add(E3);
		
		partie.setListeEquipes(listeEquipe);
		
		this.srvPartie.add(partie);
		
		model.addAttribute("listeEquipes",this.srvPartie.getAllEquipe());
		model.addAttribute("nbE1", contenu.get("listNbJ1"));
		model.addAttribute("nbE2", contenu.get("listNbJ2"));
		model.addAttribute("nbE3", contenu.get("listNbJ3"));
		
		return "redirect:/joueur";
	}
	
	@GetMapping("/joueur")
	public String joueur(){
		return "CreationJoueur";
	}
	
	@PostMapping("/joueur")
	public String joueur(@RequestParam Map<String,String> contenu, Model model){
		
		
		
		return "redirect:/partie";
	}
	
	@GetMapping("/partie")
	public String partie(){
		return "partie";
	}
	
	@PostMapping("/partie")
	public String partie(@RequestParam Map<String,String> contenu, Model model){
		
		
		return "redirect:/partie";
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

package fr.formation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

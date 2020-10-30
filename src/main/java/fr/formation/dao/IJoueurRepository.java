package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Joueur;

public interface IJoueurRepository extends JpaRepository<Joueur,Integer>{

}

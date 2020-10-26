package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Equipe;

public interface IEquipeRepository extends JpaRepository<Equipe,Integer>{

}

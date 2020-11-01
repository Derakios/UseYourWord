package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Reponse;

public interface IReponseRepository extends JpaRepository<Reponse,Integer>{

}

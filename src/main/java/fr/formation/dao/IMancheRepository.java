package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Manche;

public interface IMancheRepository extends JpaRepository<Manche,Integer>{

}

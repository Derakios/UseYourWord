package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Partie;

public interface IPartieRepository extends JpaRepository<Partie,Integer> {

}

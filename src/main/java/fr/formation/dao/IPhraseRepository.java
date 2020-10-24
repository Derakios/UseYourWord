package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Phrase;

public interface IPhraseRepository extends JpaRepository<Phrase,Integer> {

}

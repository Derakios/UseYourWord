package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Image;

public interface IImageRepository extends JpaRepository<Image,Integer> {

}

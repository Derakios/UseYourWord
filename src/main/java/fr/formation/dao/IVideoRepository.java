package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.models.Video;

public interface IVideoRepository extends JpaRepository<Video,Integer> {

}

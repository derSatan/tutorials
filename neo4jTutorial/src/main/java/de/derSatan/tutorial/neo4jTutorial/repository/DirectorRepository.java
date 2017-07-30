package de.derSatan.tutorial.neo4jTutorial.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.derSatan.tutorial.neo4jTutorial.model.Director;

public interface DirectorRepository extends GraphRepository<Director> {
    Director findById(String id);
}

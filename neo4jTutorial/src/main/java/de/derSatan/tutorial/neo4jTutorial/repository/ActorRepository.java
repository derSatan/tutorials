package de.derSatan.tutorial.neo4jTutorial.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.derSatan.tutorial.neo4jTutorial.model.Actor;

public interface ActorRepository extends GraphRepository<Actor> {

    Actor findById(String id);
}

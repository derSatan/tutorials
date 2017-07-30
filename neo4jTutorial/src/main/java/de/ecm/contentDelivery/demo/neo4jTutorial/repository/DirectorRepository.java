package de.ecm.contentDelivery.demo.neo4jTutorial.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.ecm.contentDelivery.demo.neo4jTutorial.model.Director;

public interface DirectorRepository extends GraphRepository<Director> {
    Director findById(String id);
}

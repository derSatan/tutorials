package de.derSatan.tutorial.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import de.derSatan.tutorial.reactive.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

	Flux<Person> findByFirstName(final String firstName);

	Mono<Person> findOneByFirstName(final String firstName);
}
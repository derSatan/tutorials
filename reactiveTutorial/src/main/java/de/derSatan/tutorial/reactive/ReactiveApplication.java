package de.derSatan.tutorial.reactive;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.derSatan.tutorial.reactive.model.Person;
import de.derSatan.tutorial.reactive.repository.PersonRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Override
	public void run(String args[]) {

		final Person johnAoe = new Person("john", "aoe", LocalDateTime.now(), "loser", 0);
		final Person johnBoe = new Person("john", "boe", LocalDateTime.now(), "a bit of a loser", 10);
		final Person johnCoe = new Person("john", "coe", LocalDateTime.now(), "average", 100);
		final Person johnDoe = new Person("john", "doe", LocalDateTime.now(), "winner", 1000);

		personRepository.saveAll(Flux.just(johnAoe, johnBoe, johnCoe, johnDoe)).subscribe();

		personRepository.findByFirstName("john").log().map(Person::getFirstName).subscribe(System.out::println);

		personRepository.findOneByFirstName("john").log().map(Person::getId).subscribe(System.out::println);
	}
}

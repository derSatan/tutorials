package de.ecm.contentDelivery.demo.neo4jTutorial.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.ecm.contentDelivery.demo.neo4jTutorial.model.Rating;
import de.ecm.contentDelivery.demo.neo4jTutorial.model.User;

public interface UserRepository extends GraphRepository<User>,
        CineastsUserDetailsService {

    User findByLogin(String login);

    @Query("MATCH (movie:Movie)<-[r:RATED]-(user) where ID(movie)={0} AND ID(user)={1} RETURN r")
    Rating findUsersRatingForMovie(long movieId, long userId);
}

package de.ecm.contentDelivery.demo.neo4jTutorial.service;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.ecm.contentDelivery.demo.neo4jTutorial.model.Movie;
import de.ecm.contentDelivery.demo.neo4jTutorial.model.User;
import de.ecm.contentDelivery.demo.neo4jTutorial.movieimport.MovieDbImportService;
import de.ecm.contentDelivery.demo.neo4jTutorial.repository.MovieRepository;
import de.ecm.contentDelivery.demo.neo4jTutorial.repository.UserRepository;

@Service
public class DatabasePopulator {

    private final static Logger log = LoggerFactory.getLogger(DatabasePopulator.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieDbImportService importService;

    @Transactional
    public List<Movie> populateDatabase() {
        importService.importImageConfig();
        User me = userRepository.save(new User("micha", "Micha", "password", User.SecurityRole.ROLE_ADMIN, User.SecurityRole.ROLE_USER));
        User ollie = new User("ollie", "Olliver", "password", User.SecurityRole.ROLE_USER);
        me.addFriend(ollie);
        userRepository.save(me);
        List<Integer> ids = asList(19995 , 194, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 13, 20526, 11, 1893, 1892, 1894, 168, 193, 200, 157, 152, 201, 154, 12155, 58, 285, 118, 22, 392, 5255, 568, 9800, 497, 101, 120, 121, 122);
        List<Movie> result = new ArrayList<Movie>(ids.size());
        for (Integer id : ids) {
            result.add(importService.importMovie(String.valueOf(id)));
        }

      /*  me.rate(movieRepository.findById("13"), 5, "Inspiring");
        final Movie movie = movieRepository.findById("603");
        me.rate(movie, 5, "Best of the series");*/
        return result;
    }

    @Transactional
    public void cleanDb() {
        new Neo4jDatabaseCleaner().cleanDb();
    }
}

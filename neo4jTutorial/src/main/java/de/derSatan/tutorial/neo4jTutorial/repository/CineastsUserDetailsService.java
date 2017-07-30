package de.derSatan.tutorial.neo4jTutorial.repository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import de.derSatan.tutorial.neo4jTutorial.model.User;
import de.derSatan.tutorial.neo4jTutorial.service.CineastsUserDetails;

public interface CineastsUserDetailsService extends UserDetailsService {
    @Override
    CineastsUserDetails loadUserByUsername(String login) throws UsernameNotFoundException;

    User getUserFromSession();

    @Transactional
    User register(String login, String name, String password);

    @Transactional
    void addFriend(String login, final User userFromSession);
}

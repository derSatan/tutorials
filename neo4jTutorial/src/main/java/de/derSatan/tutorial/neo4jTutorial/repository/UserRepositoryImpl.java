package de.derSatan.tutorial.neo4jTutorial.repository;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import de.derSatan.tutorial.neo4jTutorial.model.User;
import de.derSatan.tutorial.neo4jTutorial.service.CineastsUserDetails;

public class
        UserRepositoryImpl implements CineastsUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session session;

    @Override
    public CineastsUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user = findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + login);
        }
        return new CineastsUserDetails(user);
    }

    private User findByLogin(String login) {
        return IteratorUtil.firstOrNull(findByProperty("login", login).iterator());
    }

    @Override
    public User getUserFromSession() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CineastsUserDetails) {
            CineastsUserDetails userDetails = (CineastsUserDetails) principal;
            return userDetails.getUser();
        }
        return null;
    }

    @Override
    @Transactional
    public User register(String login, String name, String password) {
        User found = findByLogin(login);
        if (found != null) {
            throw new RuntimeException("Login already taken: " + login);
        }
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("No name provided.");
        }
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("No password provided.");
        }
        User user=userRepository.save(new User(login,name,password, User.SecurityRole.ROLE_USER));
        setUserInSession(user);
        return user;
    }

    void setUserInSession(User user) {
        SecurityContext context = SecurityContextHolder.getContext();
        CineastsUserDetails userDetails = new CineastsUserDetails(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        context.setAuthentication(authentication);

    }

    @Override
    @Transactional
    public void addFriend(String friendLogin, final User user) {
        User friend = findByLogin(friendLogin);
        if (!user.equals(friend)) {
            user.addFriend(friend);
            userRepository.save(user);
        }
    }

    public Iterable<User> findByProperty(String propertyName, Object propertyValue) {
        return session.loadAll(User.class, new Filter(propertyName, propertyValue));
    }

}

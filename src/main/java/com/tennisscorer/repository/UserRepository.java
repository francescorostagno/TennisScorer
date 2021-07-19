package com.tennisscorer.repository;
import com.tennisscorer.helper.MD5;
import com.tennisscorer.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     *
     * save changes made to a user, or insert it if its new
     *
     * @param user
     */
    public void save(User user) {
        em.merge(user);
    }


    /**
     * finds a user given its username
     *
     * @param username - the username of the searched user
     * @return  a matching user, or null if no user found.
     */
    public User findUserByUsername(String username) {

        List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;
    }


    public User authenticate(String username,String password) {

        List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD, User.class)
                .setParameter(1, username)
                .setParameter(2, password)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;
    }


    /**
     * checks if a username is still available in the database
     *
     * @param username - the username to be checked for availability
     * @return true if the username is still available
     */
    public boolean isUsernameAvailable(String username) {

        List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList();

        return users.isEmpty();
    }

}

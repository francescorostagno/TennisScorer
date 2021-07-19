package com.tennisscorer.service;
import com.tennisscorer.helper.MD5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tennisscorer.model.User;
import com.tennisscorer.repository.UserRepository;


@Configurable
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * creates a new user in the database
     *
     * @param username - the username of the new user
     * @param email - the user email
     * @param password - the user plain text password
     * @param role - the user role
     */
    @Transactional
    public void createUser(String username, String email, String password, int role) {
        if (!userRepository.isUsernameAvailable(username)) {
            throw new IllegalArgumentException("The username is not available.");
        }

        User user = new User(username, MD5.getMd5(password), email, 0);

        userRepository.save(user);
    }


    public ResponseEntity<User> authenticate(String username,String password){
        if (userRepository.authenticate(username,password) == null) {
            throw new IllegalArgumentException("The username is not available.");
        }
        User user = userRepository.authenticate(username,password);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}

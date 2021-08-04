package com.tennisscorer.service;

import com.tennisscorer.helper.MD5;
import com.tennisscorer.model.User;
import com.tennisscorer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


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
    public void createUser(String username, String email, String password, String role) {
        if (!userRepository.isUsernameAvailable(username)) {
            throw new IllegalArgumentException("The username is not available.");
        }
        role = "USER";
        User user = new User(username, MD5.getMd5(password), email, role );

        userRepository.save(user);
    }


    public User authenticate(String username,String password){
        return userRepository.authenticate(username,password);
    }

    public String getRoleCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (Objects.equals(cookies[i].getName(), "ROLE")){
                    return cookies[i].getValue();
                }
            }
        }
        return "NOT_ALLOWED";
    }

    @Transactional(readOnly = false)
    public ResponseEntity<User> register(String username,String password,String email){
        User user = new User(username,password, email, "USER");
        userRepository.save(user);
        return new ResponseEntity<User>( user, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public boolean checkUsername(String username) {
        return userRepository.isUsernameAvailable(username);
    }


    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}

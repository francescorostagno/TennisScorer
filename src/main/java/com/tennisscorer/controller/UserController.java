package com.tennisscorer.controller;
import com.tennisscorer.model.User;
import com.tennisscorer.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/login")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

   /* @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userService.createUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
    }*/

    @PostMapping("/authenticate")
    public ResponseEntity<User>  authenticate(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("email") String email){
        return userService.authenticate(username,password);
    }

    @PostMapping("/register")
    public ResponseEntity<User>  register(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("email") String email){
        if(userService.checkUsername(username)){
            return userService.register(username,password,email);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

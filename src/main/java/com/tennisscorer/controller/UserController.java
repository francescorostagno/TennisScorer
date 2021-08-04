package com.tennisscorer.controller;

import com.tennisscorer.model.User;
import com.tennisscorer.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

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
    public ResponseEntity<User>  authenticate(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, HttpServletResponse response){
        User user = userService.authenticate(username,password);
        if(user != null){
            Cookie cookie = new Cookie("ROLE", user.getRole());
            cookie.setPath("/");
            // add cookie to response
            response.addCookie(cookie);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }

        return "No cookies";
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

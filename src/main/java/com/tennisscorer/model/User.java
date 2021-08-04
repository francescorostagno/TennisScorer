package com.tennisscorer.model;

import javax.persistence.*;


@Entity
@Table(name = "`users`")
@NamedQueries({
        @NamedQuery(
                name = User.FIND_BY_USERNAME,
                query = "select u from User u where username = ?1"
        ),
        @NamedQuery(
                name = User.FIND_BY_USERNAME_AND_PASSWORD,
                query = "select u from User u where username = ?1 and password = ?2"
        )
})
public class User  {

    public static final String FIND_BY_USERNAME = "User.findByUserName";
    public static final String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsernameAndPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private int id;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`email`")
    private String email;

    @Column(name = "`role`")
    private String role;


    @Column(name = "enabled")
    private Boolean enabled;


    public User() {

    }

    public int getId() {
        return id;
    }

    public User(String username, String password, String email, String role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}

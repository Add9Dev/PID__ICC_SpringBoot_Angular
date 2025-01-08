package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Login must not be empty.")
    @Column(nullable = false, unique = true)
    private String login;

    @NotEmpty(message = "Password must not be empty.")
    @Column(nullable = false)
    private String password;

    private String firstname;
    private String lastname;

    @NotEmpty(message = "Email must not be empty.")
    @Column(nullable = false, unique = true)
    private String email;

    private String langue;

    @Column(nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_representations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "representation_id")
    )
    private List<Representation> representations = new ArrayList<>();

    protected User() {}

    public User(String login, String firstname, String lastname) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.created_at = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }

    public User addRole(Role role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
            role.getUsers().add(this);
        }
        return this;
    }

    public User removeRole(Role role) {
        if (this.roles.contains(role)) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }
        return this;
    }

    public User addRepresentation(Representation representation) {
        if (!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.getUsers().add(this);
        }
        return this;
    }

    public User removeRepresentation(Representation representation) {
        if (this.representations.contains(representation)) {
            this.representations.remove(representation);
            representation.getUsers().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return login + " (" + firstname + " " + lastname + ")";
    }
}

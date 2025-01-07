package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The login must not be empty.")
    @Size(min = 4, max = 30, message = "The login must be between 4 and 30 characters.")
    @Column(nullable = false, unique = true)
    private String login;

    @NotEmpty(message = "The password must not be empty.")
    @Size(min = 6, message = "The password must be at least 6 characters long.")
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "The firstname must not be empty.")
    @Size(min = 2, max = 60, message = "The firstname must be between 2 and 60 characters.")
    @Column(nullable = false)
    private String firstname;

    @NotEmpty(message = "The lastname must not be empty.")
    @Size(min = 2, max = 60, message = "The lastname must be between 2 and 60 characters.")
    @Column(nullable = false)
    private String lastname;

    @NotEmpty(message = "The email must not be empty.")
    @Email(message = "The email must be valid.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String langue;

    @PastOrPresent(message = "The creation date cannot be in the future.")
    @Column(nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @ManyToOne(optional = false) // Pleins d'utilisateur on un seul role
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}

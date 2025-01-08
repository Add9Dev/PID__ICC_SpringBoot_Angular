package be.iccbxl.pid.reservationsspringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Récupère tous les utilisateurs.
     */
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Récupère un utilisateur par son ID.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Récupère un utilisateur par son login.
     */
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * Ajoute un nouvel utilisateur après validation de l'unicité du login et de l'email.
     */
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("Login already in use.");
        }
        return userRepository.save(user);
    }

    /**
     * Met à jour un utilisateur existant.
     */
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setLogin(updatedUser.getLogin());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setFirstname(updatedUser.getFirstname());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setLangue(updatedUser.getLangue());

            // Mise à jour des rôles
            existingUser.getRoles().clear();
            updatedUser.getRoles().forEach(existingUser::addRole);

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    /**
     * Supprime un utilisateur par son ID.
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}

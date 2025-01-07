package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}

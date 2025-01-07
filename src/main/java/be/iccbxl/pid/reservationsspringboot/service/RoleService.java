package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        repository.findAll().forEach(roles::add);
        return roles;
    }

    public Role getRoleById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Role getRoleByName(String role) {
        return repository.findByRole(role);
    }

    public void addRole(Role role) {
        repository.save(role);
    }

    public void updateRole(Long id, Role updatedRole) {
        repository.findById(id).ifPresent(existingRole -> {
            existingRole.setRole(updatedRole.getRole());
            repository.save(existingRole);
        });
    }

    public void deleteRole(Long id) {
        repository.deleteById(id);
    }
}

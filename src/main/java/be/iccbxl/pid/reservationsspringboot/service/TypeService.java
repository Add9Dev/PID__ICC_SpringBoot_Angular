package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Type;
import be.iccbxl.pid.reservationsspringboot.repository.TypeRepository;

@Service
public class TypeService {
    @Autowired
    private TypeRepository repository;

    public List<Type> getAll() {
        List<Type> types = new ArrayList<>();
        repository.findAll().forEach(types::add);
        return types;
    }

    public Optional<Type> getTypeById(Long id) {
        return repository.findById(id);
    }

    public Type getTypeByName(String type) {
        return repository.findByType(type);
    }

    public void addType(Type type) {
        repository.save(type);
    }

    public void updateType(Long id, Type updatedType) {
        repository.findById(id).ifPresent(existingType -> {
            existingType.setType(updatedType.getType());
            repository.save(existingType);
        });
    }

    public void deleteType(Long id) {
        repository.deleteById(id);
    }
}

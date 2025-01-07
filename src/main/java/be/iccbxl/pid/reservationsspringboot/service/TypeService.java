package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Type;
import be.iccbxl.pid.reservationsspringboot.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository repository;

    // Récupérer tous les types
    public List<Type> getAll() {
        List<Type> types = new ArrayList<>();
        repository.findAll().forEach(types::add);
        return types;
    }

    // Récupérer un type par ID
    public Type get(String id) {
        Long indice = Long.parseLong(id);
        Optional<Type> type = repository.findById(indice);
        return type.orElse(null); // Retourne null si le type n'existe pas
    }

    // Ajouter un nouveau type
    public void add(Type type) {
        repository.save(type);
    }

    // Mettre à jour un type existant
    public void update(String id, Type type) {
        repository.save(type);
    }

    // Supprimer un type
    public void delete(String id) {
        Long indice = Long.parseLong(id);
        repository.deleteById(indice);
    }
}

package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.repository.LocalityRepository;

@Service
public class LocalityService {
    @Autowired
    private LocalityRepository repository;

    public List<Locality> getAll() {
        List<Locality> localities = new ArrayList<>();
        repository.findAll().forEach(localities::add);
        return localities;
    }

    public Optional<Locality> getById(Long id) {
        return repository.findById(id);
    }

    public Locality getByPostalCode(String postalCode) {
        return repository.findByPostalCode(postalCode);
    }

    public Locality getByLocalityName(String localityName) {
        return repository.findByLocality(localityName);
    }

    public void addLocality(Locality locality) {
        repository.save(locality);
    }

    public void updateLocality(Long id, Locality updatedLocality) {
        repository.findById(id).ifPresent(existingLocality -> {
            existingLocality.setPostalCode(updatedLocality.getPostalCode());
            existingLocality.setLocality(updatedLocality.getLocality());
            repository.save(existingLocality);
        });
    }

    public void deleteLocality(Long id) {
        repository.deleteById(id);
    }
}

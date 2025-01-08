package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data // Génère automatiquement les getters, setters, toString, hashCode, equals
@NoArgsConstructor // Génère un constructeur sans arguments pour JPA
@Entity
@Table(name = "localities")
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Le code postal ne doit pas être vide.")
    @Column(nullable = false)
    private String postalCode;

    @NotEmpty(message = "Le nom de la localité ne doit pas être vide.")
    @Column(nullable = false)
    private String locality;

    @OneToMany(mappedBy = "locality", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Évite les boucles infinies dans toString()
    private List<Location> locations = new ArrayList<>();

    // Constructeur avec arguments
    public Locality(String postalCode, String locality) {
        this.postalCode = postalCode;
        this.locality = locality;
    }

    // Méthode utilitaire pour ajouter une location
    public void addLocation(Location location) {
        if (!this.locations.contains(location)) {
            this.locations.add(location);
            location.setLocality(this); // Maintient la relation bidirectionnelle
        }
    }

    // Méthode utilitaire pour supprimer une location
    public void removeLocation(Location location) {
        if (this.locations.contains(location)) {
            this.locations.remove(location);
            location.setLocality(null); // Supprime la relation bidirectionnelle
        }
    }
}

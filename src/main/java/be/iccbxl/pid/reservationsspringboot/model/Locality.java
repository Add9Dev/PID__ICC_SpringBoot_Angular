package be.iccbxl.pid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Génère automatiquement les getters, setters, toString, hashCode, equals
@NoArgsConstructor // Génère un constructeur sans arguments pour JPA
@Entity
@Table(name = "localities")
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "The postal code must not be empty.")
    private String postalCode;

    @NotEmpty(message = "The locality name must not be empty.")
    private String locality;

    public Locality(String postalCode, String locality) {
        this.postalCode = postalCode;
        this.locality = locality;
    }
}

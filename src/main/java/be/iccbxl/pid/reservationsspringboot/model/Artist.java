package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The firstname must not be empty.")
    @Size(min = 2, max = 60, message = "The firstname must be between 2 and 60 characters long.")
    @Column(nullable = false)
    private String firstname;

    @NotEmpty(message = "The lastname must not be empty.")
    @Size(min = 2, max = 60, message = "The lastname must be between 2 and 60 characters long.")
    @Column(nullable = false)
    private String lastname;

    @ManyToMany(mappedBy = "artists")
    private List<Type> types = new ArrayList<>();

    public void addType(Type type) {
        if (!this.types.contains(type)) {
            this.types.add(type);
            type.addArtist(this); // Mise à jour bidirectionnelle
        }
    }

    public void removeType(Type type) {
        if (this.types.remove(type)) {
            type.getArtists().remove(this); // Mise à jour bidirectionnelle
        }
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", types=" + types.size() + "]";
    }
}
